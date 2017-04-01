package com.zhuazhu.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by tao on 2015/9/30.
 */
public class CommonUtils {
    /**
     * 获取设备id
     *
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     * 需要权限:android.permission.GET_TASKS
     *
     * @param context
     * @return
     */
    public static boolean isExist(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfos = am.getRunningTasks(20);
        for (ActivityManager.RunningTaskInfo taskInfo : tasksInfos) {
            if (context.getPackageName().equals(taskInfo.topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /***
     * 得到Strings.xml 中的stirng
     *
     * @param context
     * @param id
     * @return
     */
    public static String getRString(Context context, int id) {
        return context.getResources().getText(id).toString();
    }

    /*
* 判断文件是否存在
*/
    public static boolean checkFileIsExists(Context mContext, String fileName) {
        java.io.File fileDir = mContext.getFilesDir();
        String sFileName = fileDir.getParent() + java.io.File.separator
                + fileDir.getName() + "/" + fileName;
        java.io.File file = new java.io.File(sFileName);
        return file.isFile();
    }

    /***
     * 对象序列化
     *
     * @param obj
     * @return 可能为null
     * @throws IOException
     */
    public static byte[] objectToByte(Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        byte[] by = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            by = baos.toByteArray();
        } catch (IOException e) {
            return by;
        } finally {
            try {
                if(oos!=null){
                    oos.close();
                }
                if(baos!=null){
                    baos.close();
                }
            } catch (IOException e) {
                return by;
            }
        }

        return by;
    }

    /***
     * 反序列化
     *
     * @param by
     * @return 可能为null
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjectFromByte(byte[] by, T t) {
        ByteArrayInputStream bais = new ByteArrayInputStream(by);
        ObjectInputStream oos = null;
        T obj = null;
        try {
            oos = new ObjectInputStream(bais);
            obj = (T) oos.readObject();
        } catch (Exception e) {
        } finally {
            try {
                if(oos!=null){
                    oos.close();
                }
                if(bais!=null){
                    bais.close();
                }
            } catch (IOException e) {
            }
        }

        return obj;
    }

    /***
     * Base64加密
     */

    @SuppressWarnings("deprecation")
    public static String getBase64Code(String param) {
        return URLEncoder.encode(param);
    }

    /**
     * 获取版本code
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static int getVersionCode(Context context) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(
                context.getPackageName(), 0);
        int version = packInfo.versionCode;
        return version;
    }

    /**
     * 获取版本code
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getVersionName(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(packInfo==null){
            return null;
        }
        return packInfo.versionName;
    }


}
