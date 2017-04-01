package com.zhuazhu.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhuazhu.R;


public class ChoosePictureDialog extends Dialog implements android.view.View.OnClickListener{
	private android.view.View.OnClickListener listener;
	/**
	 * 弹出确认,取消框
	 * tag:
	 * 1:相机
	 * 2:相册
	 * @param context
	 * @param listener
	 */
	public ChoosePictureDialog(Context context, android.view.View.OnClickListener listener) {
		super(context,R.style.custom_dialog);
		this.listener = listener;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_choose_picture);
		/* 
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置,
         * 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setGravity(Gravity.BOTTOM);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_top_animation);
        ll.startAnimation(animation);
        TextView camera = (TextView) findViewById(R.id.camera);
        TextView picture = (TextView) findViewById(R.id.picture);
        TextView cancel = (TextView) findViewById(R.id.cancel);
        camera.setOnClickListener(this);
        picture.setOnClickListener(this);
        cancel.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		cancel();
		if(id==R.id.camera){
			v.setTag(1);
			if(listener!=null){
				listener.onClick(v);
			}
		}else if(id==R.id.picture){
			v.setTag(2);
			if(listener!=null){
				listener.onClick(v);
			}
		}
	}

}
