package com.example.admin.myapplication_app.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Discription:
 * Created by guokun on 2019/7/30.
 */
public class MyClipView extends View {

    private Paint paint;

    //优化 onDraw 局部变量
    private int width;
    private int height;

    public MyClipView(Context context) {
        this(context, null);
    }

    public MyClipView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawOutRect(canvas);
        drawIntersect(canvas);
    }

    private void drawDiff(Canvas canvas) {
        canvas.save();
        canvas.translate(10, 10);
        canvas.clipRect(0, 0, 100, 100);
        canvas.clipRect(30, 30, 70, 70, Region.Op.DIFFERENCE);
        drawBg(canvas);
        canvas.restore();
    }

    private void drawBg(Canvas canvas) {
        canvas.clipRect(0, 0, 100, 100);

        canvas.drawColor(Color.WHITE);

        paint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, paint);

        paint.setColor(Color.GREEN);
        canvas.drawCircle(30, 70, 30, paint);

        paint.setColor(Color.BLUE);
        canvas.drawText("Clipping", 50, 50, paint);
    }

    private void drawIntersect(Canvas canvas) {
        canvas.save();
        canvas.translate(10, 10);
        canvas.clipRect(0, 0, 100, 100);
        canvas.clipRect(30, 30, 70, 70, Region.Op.INTERSECT);
        drawBg(canvas);
        canvas.restore();
    }

    private void drawOutRect(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawRect(new Rect(
                0,
                0,
                width,
                height
        ), paint);
    }
}
