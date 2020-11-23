package com.example.admin.myapplication_app.viewdrager;

import android.util.Log;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

/**
 * Discription:
 * Created by guokun on 2020/11/23.
 */
public class ViewPagerUtils {
    public static View getCurrentView(ViewPager viewPager) {
        try {
            final int currentItem = viewPager.getCurrentItem();
            for (int i = 0; i < viewPager.getChildCount(); i++) {
                final View child = viewPager.getChildAt(i);
                final ViewPager.LayoutParams layoutParams = (ViewPager.LayoutParams) child.getLayoutParams();
                //NoSuchFieldException
                Field f = layoutParams.getClass().getDeclaredField("position");
                f.setAccessible(true);
                int position = (Integer) f.get(layoutParams); //IllegalAccessException

                if (!layoutParams.isDecor && currentItem == position) {
                    return child;
                }
            }
        } catch (NoSuchFieldException e) {
            Log.e("ViewPagerUtils", e.toString());
        } catch (IllegalArgumentException e) {
            Log.e("ViewPagerUtils", e.toString());
        } catch (IllegalAccessException e) {
            Log.e("ViewPagerUtils", e.toString());
        }
        return null;
    }
}
