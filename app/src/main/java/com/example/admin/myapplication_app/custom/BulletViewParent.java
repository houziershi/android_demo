package com.example.admin.myapplication_app.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Discription:
 *
 * @author guokun
 * @date 2019/4/23
 */
public class BulletViewParent extends FrameLayout {


    private int measuredWidth;
    private int measuredHeight;

    public BulletViewParent(@NonNull Context context) {
        super(context);
    }

    public BulletViewParent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BulletViewParent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    public void childViewAutoMove() {
        final int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                final View child = getChildAt(i);
                if (child instanceof CustomBulletView) {
                    ((CustomBulletView) child).startAutoMove(getMeasuredWidth(),getMeasuredHeight(),this);
                }
            }
        }
    }


}
