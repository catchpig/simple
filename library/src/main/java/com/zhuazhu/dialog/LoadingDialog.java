package com.zhuazhu.dialog;

import android.app.Dialog;
import android.content.Context;

import com.zhuazhu.R;


public class LoadingDialog {

	
	public static Dialog show(Context context) {
		Dialog loadingDialog = new Dialog(context, R.style.custom_dialog);// 创建自定义样式dialog
		loadingDialog.setCancelable(false);// 不可以用“返回键”取消
		loadingDialog.setContentView(R.layout.dialog_loading);
		loadingDialog.show();
		return loadingDialog;
	}
}
