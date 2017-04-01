package com.zhuazhu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhuazhu.R;
import com.zhuazhu.util.StringUtils;


public class ConfirmDialog extends Dialog implements View.OnClickListener{
	private View.OnClickListener listener;
	private String title;
	/**
	 * 弹出确认,取消框
	 * tag:
	 * 1:确认按钮的点击回调
	 * 2:取消按钮的点击毁掉
	 * @param context
	 * @param title
	 * @param listener
	 */
	public ConfirmDialog(Context context, boolean cancelable, String title, View.OnClickListener listener) {
		super(context, R.style.custom_dialog);
		this.listener = listener;
		this.title = title;
		setCancelable(cancelable);
	}
	/**
	 * 弹出确认,取消框
	 * tag:
	 * 1:确认按钮的点击回调
	 * 2:取消按钮的点击回调
	 * @param context
	 * @param title
	 * @param listener
	 */
	public ConfirmDialog(Context context, String title, View.OnClickListener listener) {
		super(context, R.style.custom_dialog);
		this.listener = listener;
		this.title = title;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_confirm);
		TextView tle = (TextView) findViewById(R.id.title);
		if(StringUtils.isEmpty(title)){
			tle.setText("");
		}else{
			tle.setText(title);
		}
		TextView submit = (TextView) findViewById(R.id.submit);
		TextView cancel = (TextView) findViewById(R.id.cancel);
		submit.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		if(id== R.id.submit){
			cancel();
			v.setTag(1);
			listener.onClick(v);
		}else if(id== R.id.cancel){
			cancel();
			v.setTag(2);
			listener.onClick(v);
		}
		
	}
	
}
