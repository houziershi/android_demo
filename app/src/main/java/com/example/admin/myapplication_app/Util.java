package com.example.admin.myapplication_app;

import android.content.Context;

import com.example.admin.myapplication_app.data.Note;

/**
 * Discription:
 * Created by guokun on 2019/4/23.
 */
public class Util {

    /**
     * dip值转px
     */
    public static int dip2px(Context context, double d) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (d * scale + 0.5f);

    }

    /**
     * 获得设备屏幕宽度 单位像素
     */
    public static int getDeviceWidthPixels(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得设备屏幕高度 单位像素
     */
    public static int getDeviceHeightPixels(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static void main(String[] args) {
        Note note1 = new Note();
        note1.setEntityId(2L);
        note1.setChapterId(1L);
        Note note2 = new Note();
        note2.setEntityId(2L);
        note2.setEntityId(1L);



    }
}
