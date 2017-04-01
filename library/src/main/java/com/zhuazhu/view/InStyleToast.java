package com.zhuazhu.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuazhu.R;


public class InStyleToast {

    public static Toast makeText(Context context, CharSequence text, int duration) {  
        Toast result = new Toast(context);  
          
        //获取LayoutInflater对象  
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
        //由layout文件创建一个View对象  
        View layout = inflater.inflate(R.layout.toast_in_style_layout, null);
          
        //实例化ImageView和TextView对象  
        TextView textView = (TextView) layout.findViewById(R.id.tip);  
           
        textView.setText(text);
        result.setView(layout);  
       // result.setGravity(Gravity.CENTER_VERTICAL, 0, 0);  
        result.setDuration(duration);  
          
        return result;  
    }
	
}
