package com.zhuazhu.util;

/**
 * Created by tao on 2016/11/23.
 */

public class Log {
    private static final boolean flag = true;
    public static void e(String tag,String msg){
        if(flag){
            android.util.Log.e(tag,msg);
        }
    }
    public static void e(String tag,String msg,Throwable t){
        if(flag){
            android.util.Log.e(tag,msg,t);
        }
    }
    public static void i(String tag,String msg){
        if(flag){
            android.util.Log.i(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if(flag){
            android.util.Log.d(tag,msg);
        }
    }
    public static void w(String tag,String msg){
        if(flag){
            android.util.Log.w(tag,msg);
        }
    }
    public static void w(String tag,Throwable e){
        if(flag){
            android.util.Log.w(tag,e);
        }
    }
    public static void w(String tag,String msg,Throwable e){
        if(flag){
            android.util.Log.w(tag,msg,e);
        }
    }
}
