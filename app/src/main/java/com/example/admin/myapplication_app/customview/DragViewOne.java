package com.example.admin.myapplication_app.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;

import com.example.admin.myapplication_app.R;

public class DragViewOne extends LinearLayout {
    private static final String TAG = "DragViewOne";

    private TextView dragView;
    private TextView backView;
    private TextView edgeView;
    private int backViewLeft;
    private int backViewTop;

    private ViewDragHelper mDragger;

    public DragViewOne(Context context) {
        this(context, null);
    }

    public DragViewOne(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        backViewLeft = backView.getLeft();
        backViewTop = backView.getTop();
    }

    private void init() {
        mDragger = ViewDragHelper.create(this, 1f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return dragView.getId() == child.getId() || backView.getId() == child.getId();
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                Log.i(TAG, " <clampViewPositionHorizontal>.......left=" + left + "....dx=" + dx);
                if (left < getPaddingLeft()) {
                    return getPaddingLeft();
                }
                if (left > getMeasuredWidth() - getPaddingRight() - child.getMeasuredWidth()) {
                    return getMeasuredWidth() - getPaddingRight() - child.getMeasuredWidth();
                }
                return left;
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                if (top < getPaddingTop()) {
                    return getPaddingTop();
                }
                if (top > getMeasuredHeight() - getPaddingBottom() - child.getMeasuredHeight()) {
                    return getMeasuredHeight() - getPaddingBottom() - child.getMeasuredHeight();
                }
                return top;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                if (releasedChild.getId() == backView.getId()) {
                    mDragger.settleCapturedViewAt(backViewLeft, backViewTop);
                    invalidate();
                }
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                mDragger.captureChildView(edgeView, pointerId);
            }
        });
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return mDragger.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragView = findViewById(R.id.drag_tv);
        backView = findViewById(R.id.back_tv);
        edgeView = findViewById(R.id.edge_tv);

    }
}
