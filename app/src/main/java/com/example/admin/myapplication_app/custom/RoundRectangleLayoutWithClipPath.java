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

    private float triangle = 0f;

    //优化 onDraw 局部变量
    private int width;
    private int height;

    private Paint paint;
    private Path triPath;
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
        triangle = array.getDimension(R.styleable.RoundRectangleLayoutWithClipPath_triangle, 0f);
        showStroke = array.getBoolean(R.styleable.RoundRectangleLayoutWithClipPath_clip_path_show, false);
        array.recycle();
        mPath = new Path();
        paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
        paint.setAntiAlias(true);
        paint.setDither(true);

        triPath = new Path();
        setWillNotDraw(false);
    }

    public void setRate(float newRate) {
        this.rate = newRate;
        //为保持边界宽度一致， 计算时要考虑到这个DEFAULT_STROKE_WIDTH / 2

        if (triangle != 0) {
            mRectF = new RectF(width / 8 * 3 - rate * width / 8 * 3 + DEFAULT_STROKE_WIDTH / 2,
                    triangle,
                    rate * width / 8 * 3 + width / 8 * 5 - DEFAULT_STROKE_WIDTH / 2,
                    height);
        } else {
            mRectF = new RectF(width / 8 * 3 - rate * width / 8 * 3 + DEFAULT_STROKE_WIDTH / 2,
                    0,
                    rate * width / 8 * 3 + width / 8 * 5 - DEFAULT_STROKE_WIDTH / 2,
                    height);
        }

        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        //画 裁剪区域
        super.dispatchDraw(canvas);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        if (triangle != 0) {
            mRectF = new RectF(width / 8 * 3 + DEFAULT_STROKE_WIDTH / 2, triangle, width / 8 * 5 - DEFAULT_STROKE_WIDTH / 2, height);
        } else {
            mRectF = new RectF(width / 8 * 3 + DEFAULT_STROKE_WIDTH / 2, 0, width / 8 * 5 - DEFAULT_STROKE_WIDTH / 2, height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i("hgk", "......<onDraw>........" + height + "..........showStroke=======" + showStroke);
        if (showStroke && triangle != 0) {
//            paint.setColor(Color.BLACK); 1.732
            //实例化路径
            triPath.moveTo(width / 8 * 4, triangle);// 此点为多边形的起点
            triPath.lineTo(width / 8 * 5, triangle);
            triPath.lineTo(width / 16 * 7, 0);
            triPath.close(); // 使这些点构成封闭的多边形
            canvas.drawPath(triPath, paint);
            canvas.translate(0, triangle);
        }
        mPath.reset();
        mPath.addRoundRect(mRectF, rids, Path.Direction.CW);
        canvas.setDrawFilter(paintFlagsDrawFilter);
        canvas.save();
        canvas.clipPath(mPath);

        if (showStroke) {
            //画圆角矩形边框
//            Paint paint = new Paint();
            canvas.drawPath(mPath, paint);
        }
//        canvas.drawPath(mPath, paint);
        canvas.restore();
    }
}
