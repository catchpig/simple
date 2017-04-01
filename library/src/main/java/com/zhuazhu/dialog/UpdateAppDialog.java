package com.zhuazhu.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import com.zhuazhu.R;
import com.zhuazhu.util.Download;

import java.io.File;

@SuppressLint("HandlerLeak")
public class UpdateAppDialog extends Dialog {

	ProgressBar bar;
	private Context context;

	private String appUrl;
	
	private String appFile="merchant.apk";
	private String appDir="apk/";

	public UpdateAppDialog(Context context, String appUrl) {
		super(context, R.style.custom_dialog);
		this.context = context;
		this.appUrl = appUrl;
		setContentView(R.layout.dialog_download_app);
		setCancelable(false);
		initView();

	}

	int maxL;

	private void initView() {
		bar = (ProgressBar) findViewById(R.id.pro);
		final Download l = new Download(appUrl);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 另起线程执行下载，安卓最新sdk规范，网络操作不能再主线程。
				maxL = l.getLength();
				bar.setMax(maxL);
				/**
				 * 下载文件到sd卡，虚拟设备必须要开始设置sd卡容量
				 * downhandler是Download的内部类，作为回调接口实时显示下载数据
				 */
				l.down2sd(appDir, appFile,
						new Download.DownloadHandler() {
							@Override
							public void setSize(int size) {

								Message message = new Message();
								message.what = size;
								handler.sendMessage(message);
							}
						});

			}
		}).start();
	}

	final Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			// 这里就一条消息
			bar.setProgress(bar.getProgress() + msg.what);
			if (bar.getProgress() >= maxL) {
				// 结束下载进度框
				installApk();
				dismiss();
			}
		}
	};

	/**
	 * 安装APK文件
	 */
	private void installApk() {
		File apkfile = new File(Environment.getExternalStorageDirectory() + "/"+appDir, appFile);
		if (!apkfile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		context.startActivity(i);
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
