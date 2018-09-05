package com.example.admin.myapplication_app.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Discription: 绘制刻度
 * 自定义动画：https://blog.csdn.net/tianjian4592
 * Created by guokun on 2018/9/5.
 */
public class MyTickMark extends View {
    private Paint paint;
    private int divide = 10;
    private int lineL = 150;
    private int lineM = 100;
    private int lineS = 80;

    public MyTickMark(Context context) {
        super(context);
    }

    public MyTickMark(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTickMark(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画外框
        drawOutRect(canvas);

        //画刻度
        drawMark(canvas);
    }

    private void drawMark(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int markNum = width / divide;
        canvas.save();
        for (int i = 0; i < markNum; i++) {
            canvas.translate(divide, 0);
            int line = lineS;
            if (i % 10 == 0) {
                line = lineL;
            }
            if (i % 5 == 0) {
                line = lineM;
            }
            canvas.drawLine(0, height, 0, height - line, paint);
        }
        canvas.restore();
    }

    private void drawOutRect(Canvas canvas) {

        canvas.drawRect(new Rect(
                0,
                0,
                getMeasuredWidth(),
                getMeasuredHeight()
        ), paint);
    }
}
