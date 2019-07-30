package com.example.admin.myapplication_app.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Region;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Discription:
 * Created by guokun on 2019/7/30.
 */
public class MyClipLinearLayout extends LinearLayout {
    private Paint paint;

    public MyClipLinearLayout(Context context) {
        this(context, null);
    }

    public MyClipLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyClipLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawBg(canvas);
//        drawIntersect(canvas);
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
}
