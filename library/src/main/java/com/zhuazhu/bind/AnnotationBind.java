package com.zhuazhu.bind;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuazhu.annotation.LayoutId;
import com.zhuazhu.annotation.util.AnnotionUtil;
import com.zhuazhu.util.LogUtils;

/**
 * 创建时间:2017/4/2 18:11
 * 创建人: 李涛
 * 修改人:
 * 修改时间:
 * 描述:注解注入工具
 */

public class AnnotationBind {
    private static final String TAG = "AnnotationBind";

    /**
     * 注入activity的布局文件
     * @param activity
     */
    public static void inject(Activity activity){
        layoutId(activity);
    }

    /**
     * 初始化layout
     * @param activity
     */
    private static void layoutId(Activity activity){
        LayoutId layoutId = AnnotionUtil.layoutId(activity.getClass());
        if (layoutId != null) {
            activity.setContentView(layoutId.value());
        } else {
            LogUtils.i(TAG, "layoutId为空,请设置layoutId");
        }
    }

    /**
     * 注入fragment的布局文件
     * @param fragment
     * @param inflater
     * @param container
     * @return 当前注解文件的View
     */
    public static View inject(Fragment fragment, LayoutInflater inflater,
                              ViewGroup container){
        LayoutId layoutId = AnnotionUtil.layoutId(fragment.getClass());
        View v = null;
        if(layoutId!=null){
            v = inflater.inflate(layoutId.value(),container,false);
        }else{
            LogUtils.i(TAG,"layoutId为空,请设置layoutId");
        }
        return v;
    }

}
