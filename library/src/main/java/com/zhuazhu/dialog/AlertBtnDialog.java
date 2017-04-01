package com.zhuazhu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhuazhu.R;


public class AlertBtnDialog extends Dialog implements View.OnClickListener{
	private View.OnClickListener listener;
	private String title;
	private boolean flag = false;
	public AlertBtnDialog(Context context, String title) {
		super(context, R.style.custom_dialog);
		this.title = title;
	}
	public AlertBtnDialog(Context context, String title, View.OnClickListener listener) {
		super(context, R.style.custom_dialog);
		this.listener = listener;
		this.title = title;
	}
	public AlertBtnDialog(Context context, String title,boolean flag, View.OnClickListener listener) {
		super(context, R.style.custom_dialog);
		this.listener = listener;
		this.title = title;
		this.flag = flag;
	}
	private TextView btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCancelable(flag);
		setContentView(R.layout.dialog_alert_btn);
		TextView ttl = (TextView) findViewById(R.id.title);
		ttl.setText(title);
		this.btn = (TextView) findViewById(R.id.submit);
		this.btn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		this.cancel();
		if(listener!=null){
			listener.onClick(v);
		}
	}
}
