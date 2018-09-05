package com.example.admin.myapplication_app.progressbar;

import android.animation.ValueAnimator;
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
 * Discription:
 * Created by guokun on 2018/9/5.
 */
public class MyLineIndicator extends View {
    private Paint paint;
    private int indicatorWidth = 20;
    private int indicatorHeight = 150;

    public static final float SCALE = 1.0f;

    float[] scaleYFloats = new float[]{SCALE,
            SCALE,
            SCALE,
            SCALE,
            SCALE,};

    private int width;
    private int height;
    private int margin;
    private RectF rectF;

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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        margin = width / 11;
        rectF = new RectF(0, (height - indicatorHeight) / 2, -margin, (height + indicatorHeight) / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawOutRect(canvas);

        canvas.save();
        for (int i = 0; i < 5; i++) {
            canvas.translate(margin * 2, 0);
            canvas.scale(SCALE, scaleYFloats[i], width / 2, height / 2);

            canvas.drawRoundRect(rectF, 100, 100, paint);
        }
        canvas.restore();
    }

    public void starAnimator() {
        long[] delays = new long[]{100, 200, 300, 400, 500};
        for (int i = 0; i < 5; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.6f, 1);
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleYFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            scaleAnim.start();
        }
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
