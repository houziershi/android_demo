package com.example.admin.myapplication_app.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Discription:
 * Created by guokun on 2018/9/5.
 */
public class MyLineIndicator extends View {
    private Paint paint;
    public MyLineIndicator(Context context) {
        super(context);
    }

    public MyLineIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLineIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {


    }
}
