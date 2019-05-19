package com.example.admin.myapplication_app.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Discription:绘制进度圆环
 * Created by guokun on 2018/9/5.
 */
public class MyProgressCircle extends View {

    private Paint textPaint;
    private Paint circlePaint;
    private Paint arcPaint;

    private int textSize = 25;
    private int circleRadius = 40;
    private int ringWidth = 15;

    private int ringRadius = 80;
    private int sweepAngle = 0;

    //优化 onDraw 局部变量
    private int width;
    private int height;
    private RectF rectF;

    public MyProgressCircle(Context context) {
        super(context);
    }

    public MyProgressCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        //文字画笔
        textPaint = new Paint();
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

        //圆 画笔
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));

        //圆弧 画笔
        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(ringWidth);
        arcPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        rectF = new RectF(width / 2 - ringRadius, height / 2 - ringRadius, width / 2 + ringRadius, height / 2 + ringRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawOutRect(canvas);

        //绘制圆
        canvas.drawCircle(width / 2, height / 2, circleRadius, circlePaint);

        //绘制进度圆环
//        canvas.drawCircle(width / 2, height / 2, ringRadius, arcPaint);

        canvas.drawArc(rectF, 0, sweepAngle, false, arcPaint);
        if (sweepAngle < 355) {
            sweepAngle += 5;
        } else {
            sweepAngle = 5;
        }

        //绘制文字
        canvas.drawText(Integer.toString(sweepAngle), width / 2, height / 2, textPaint);
        postInvalidateDelayed(100);
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
