package com.example.admin.myapplication_app.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Discription:仿照喜马拉雅FM音频弹幕
 *
 * @author guokun
 * @date 2019/4/23
 */
public class CustomBulletView extends View {

    private int circleRadius = 40;
    private int circleColor = Color.BLUE;
    private int x_radius = 60;
    private int y_radius = 60;
    private String text;
    private int centerMargin = 10;
    private Paint paint;
    private int mWidth;
    private int mHeight;
    private RectF mRectF;

    public CustomBulletView(Context context) {
        super(context);
        initPaint();
    }

    public CustomBulletView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomBulletView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mRectF = new RectF(centerMargin + circleRadius * 2, 0, mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画外框
        drawOutRect(canvas);
        //画圆
        canvas.drawCircle(circleRadius,circleRadius,circleRadius, paint);

        //画文字背景
        canvas.drawRoundRect(mRectF, x_radius, y_radius, paint);

    }

    private void drawOutRect(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawRect(new Rect(
                0,
                0,
                getMeasuredWidth(),
                getMeasuredHeight()
        ), paint);
    }
}
