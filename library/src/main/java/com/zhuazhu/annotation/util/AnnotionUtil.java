package com.zhuazhu.annotation.util;

import com.zhuazhu.annotation.LayoutId;
import com.zhuazhu.annotation.Screen;

/**
 * 创建时间:2017/4/2 18:18
 * 创建人: 李涛
 * 修改人:
 * 修改时间:
 * 描述:获取注解内容工具类
 */

public class AnnotionUtil {
    /**
     * 获取屏幕注解
     * @param cls
     * @return
     */
    public static Screen screen(Class<?> cls){
        if (cls == null) {
            return null;
        }
        Screen screen = null;
        if(cls.isAnnotationPresent(Screen.class)){
            screen = cls.getAnnotation(Screen.class);
        }
        if (screen == null) {
            return screen(cls.getSuperclass());
        }
        return screen;
    }

    /**
     * 获取布局文件的注解
     * @param cls
     * @return
     */
    public static LayoutId layoutId(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LayoutId layoutId = null;
        if(cls.isAnnotationPresent(LayoutId.class)){
            layoutId = cls.getAnnotation(LayoutId.class);
        }
        if (layoutId == null) {
            return layoutId(cls.getSuperclass());
        }
        return layoutId;
    }
}
