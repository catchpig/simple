package com.zhuazhu.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 创建时间:2017/3/31 13:49
 * 创建人: 李涛
 * 修改人:
 * 修改时间:
 * 描述:全屏注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Screen {

    boolean fullScreen() default false;
}
