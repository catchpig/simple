package com.zhuazhu.bind;

import android.app.Activity;

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
        LayoutId layoutId = AnnotionUtil.layoutId(activity.getClass());
        if (layoutId != null) {
            activity.setContentView(layoutId.value());
        } else {
            LogUtils.i(TAG, "layoutId为空,请设置layoutId");
        }
    }

}
