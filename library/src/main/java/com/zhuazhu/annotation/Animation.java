package com.zhuazhu.annotation;

import android.support.annotation.AnimRes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间:2017/8/11 11:13<br/>
 * 创建人: 李涛<br/>
 * 修改人: 李涛<br/>
 * 修改时间: 2017/8/11 11:13<br/>
 * 描述:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface Animation {
    @AnimRes
    int value();
}
