package com.zhuazhu.util;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Hashtable;

/**
 * 条形码,二维码图片生成工具
 * Created by tao on 2015/10/9.
 */
public class CodeUtils {
    /**
     * 生成二维码
     * @param text
     * @param size
     * @return
     */
    public static Bitmap createTwoCode(String text, int size) {
        Bitmap bitmap = null;
        try {
            // 需要引入core包
            QRCodeWriter writer = new QRCodeWriter();

            if (text == null || "".equals(text) || text.length() < 1) {
                return null;
            }


            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix = writer.encode(text,
                    BarcodeFormat.QR_CODE, size, size, hints);
            int w = bitMatrix.getWidth();
            int h = bitMatrix.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * w + x] = 0xff000000;
                    } else {
                        pixels[y * w + x] = 0xffffffff;
                    }
                }
            }

            bitmap = Bitmap.createBitmap(w, h,
                    Bitmap.Config.ARGB_8888);

            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);

        } catch (WriterException e) {
            Log.e(CodeUtils.class.getName(), e.toString());
        }
        return bitmap;
    }

    /**
     * 生成一维码
     * @param text
     * @param width
     * @param height
     * @return
     */
    public static Bitmap createOneCode(String text, int width, int height){
        Bitmap bitmap = null;
        try{
            BitMatrix matrix=new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, width, height);
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    }else {
                        pixels[y * width + x] = 0xffffffff;
                    }
                }
            }
            bitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        }catch (Exception e){
            Log.e(CodeUtils.class.getName(), e.toString());
        }
        return bitmap;
    }
}
