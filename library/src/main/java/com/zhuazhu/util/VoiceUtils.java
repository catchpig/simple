package com.zhuazhu.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;

/**
 * Created by tao on 2016/11/24.
 * 语音播放工具
 */

public class VoiceUtils {
    /**
     * 播放语音
     * @param context
     * @param raw
     */
    public static void play(Context context,@RawRes int raw){
        MediaPlayer player = MediaPlayer.create(context,raw);
        player.start();
    }
    /**
     * 播放语音(按顺序播放)
     * @param context
     * @param raw
     */
    public synchronized static void playSynchronized(Context context,@RawRes int raw){
        MediaPlayer player = MediaPlayer.create(context,raw);
        player.start();
    }
}
