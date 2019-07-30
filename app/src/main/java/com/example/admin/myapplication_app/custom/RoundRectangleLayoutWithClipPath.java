package com.example.admin.myapplication_app.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.admin.myapplication_app.R;

/**
 * @author admin
 */
public class RoundRectangleLayoutWithClipPath extends LinearLayout {

    private float DEFAULT_STROKE_WIDTH = 6;
    private Path mPath;
    private RectF mRectF;
    private float rate;
    private boolean showStroke;
    /**
     * 利用clip剪切的四个角半径，八个数据分别代表左上角（x轴半径，y轴半径），右上角（**），右下角（**），左下角（**）
     */
    private float[] rids = new float[8];
    private PaintFlagsDrawFilter paintFlagsDrawFilter;

    public RoundRectangleLayoutWithClipPath(Context context) {
        this(context, null);
    }

    public RoundRectangleLayoutWithClipPath(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectangleLayoutWithClipPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundRectangleLayoutWithClipPath);
        /**
         * 此处圆角半径为四个角的半径
         */
        float mRadius = array.getDimension(R.styleable.RoundRectangleLayoutWithClipPath_clip_path_radius_corner, 10);
        rids[0] = mRadius;
        rids[1] = mRadius;
        rids[2] = mRadius;
        rids[3] = mRadius;
        rids[4] = mRadius;
        rids[5] = mRadius;
        rids[6] = mRadius;
        rids[7] = mRadius;

        rate = array.getFloat(R.styleable.RoundRectangleLayoutWithClipPath_clip_path_rate, 0.5f);
        showStroke = array.getBoolean(R.styleable.RoundRectangleLayoutWithClipPath_clip_path_show, false);
        array.recycle();
        mPath = new Path();
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    public void setRate(float rate) {
        this.rate = rate;
        //为保持边界宽度一致， 计算时要考虑到这个DEFAULT_STROKE_WIDTH / 2
        mRectF = new RectF(getWidth() / 8 * 3 - rate * getWidth() / 8 * 3 + DEFAULT_STROKE_WIDTH / 2,
                0,
                rate * getWidth() / 8 * 3 + getWidth() / 8 * 5 - DEFAULT_STROKE_WIDTH / 2,
                getHeight());
        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        //画 裁剪区域
        mPath.reset();
        mPath.addRoundRect(mRectF, rids, Path.Direction.CW);
        canvas.setDrawFilter(paintFlagsDrawFilter);
        canvas.save();
        canvas.clipPath(mPath);
        if (showStroke) {
            //画圆角矩形边框
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
            paint.setAntiAlias(true);
            paint.setDither(true);
            canvas.drawPath(mPath, paint);
        }
        super.dispatchDraw(canvas);
        canvas.restore();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF = new RectF(w / 8 * 3 + DEFAULT_STROKE_WIDTH / 2, h, w / 8 * 5 - DEFAULT_STROKE_WIDTH / 2, h);
    }
}
