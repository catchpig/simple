package com.zhuazhu.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tao on 2016/4/14.
 */
public class RadarView extends View {

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, AttributeSet attrs,
                     int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //初始化
    private void init() {
//        count = Math.min(data.length, titles.length);

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint();
        textPaint.setTextSize(25);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setColor(Color.WHITE);
    }

//    private int count = 4;                //数据个数
    private float angle;
    private float radius;                   //网格半径
    private int centerX;                  //中心X
    private int centerY;                  //中心Y
    private String[] titles = {"a", "b", "c", "d","e"};
    private int[] imgs;
    private double[] data = {80, 60, 60, 60,50}; //各维度分值
    private float maxValue = 100;             //数据最大值


    private Paint mainPaint;                //雷达区画笔
    private Paint valuePaint;               //数据区画笔
    private Paint textPaint;                //文本画笔

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = (Math.min(h, w) / 2f) * 0.7f;
        //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        angle = (float) (Math.PI * 2 / data.length);
        drawRegion(canvas);
    }
    public static Bitmap getBitmapByResource(Resources resources, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        options.inSampleSize = caculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    private static int caculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqheight) {
        if (reqWidth <= 0 || reqheight <= 0) {
            return 1;
        }
        int inSampleSize = 1;
        final int bitmapWidth = options.outWidth;
        final int bitmapHeight = options.outHeight;
        if (bitmapWidth > reqWidth || bitmapHeight > reqheight) {
            final int bitmapHalfWidth = bitmapWidth / 2;
            final int bitmapHalfHeight = bitmapHeight / 2;
            while ((bitmapHalfWidth / inSampleSize) > reqWidth && (bitmapHalfHeight / inSampleSize) > reqheight) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        Path lpath = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < data.length; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i+Math.PI/6) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i+Math.PI/6) * percent);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
            //绘制直线
            lpath.reset();
            lpath.moveTo(centerX, centerY);
            lpath.lineTo(x, y);
            canvas.drawPath(lpath, mainPaint);
            //绘制文字
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            float fontHeight = fontMetrics.descent - fontMetrics.ascent;
            float dis = textPaint.measureText(titles[i]);//文本长度
            int bw = 0;
            int bh = 0;
            if(imgs!=null){
                Bitmap b = getBitmapByResource(getResources(), imgs[i], 0, 0);
                bw = b.getWidth();
                bh = b.getHeight();
                if (angle * i >= 0 && angle * i <= Math.PI / 2) {//第4象限
                    canvas.drawBitmap(b,x+(dis-bw)/2,y-bh-fontHeight,textPaint);
                } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {//第1象限
                    canvas.drawBitmap(b,x+(dis-bw)/2,y-bh-fontHeight,textPaint);
                } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {//第3象限
                    canvas.drawBitmap(b,x - bw, y+4,textPaint);
                } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {//第2象限
                    canvas.drawBitmap(b,x - (dis+bw)/2, y-fontHeight-bh,textPaint);
                }
            }
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {//第4象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {//第1象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {//第3象限
                canvas.drawText(titles[i], x - (dis+bw)/2, y+fontHeight+bh+4, textPaint);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {//第2象限
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }
            //绘制小圆点
//            canvas.drawCircle(x, y, 10, valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }

    //设置标题
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    //设置数值
    public void setData(double[] data) {
        this.data = data;
    }

    public void setImgs(int[] imgs) {
        this.imgs = imgs;
    }

    //设置最大数值
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    //设置蜘蛛网颜色
    public void setMainPaintColor(int color) {
        mainPaint.setColor(color);
    }

    //设置标题颜色
    public void setTextPaintColor(int color) {
        textPaint.setColor(color);
    }

    //设置覆盖局域颜色
    public void setValuePaintColor(int color) {
        valuePaint.setColor(color);
    }

}
