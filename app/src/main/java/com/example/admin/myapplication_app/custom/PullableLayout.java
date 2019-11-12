package com.example.admin.myapplication_app.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.OverScroller;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.myapplication_app.R;
import com.example.admin.myapplication_app.Util;

/**
 * Discription:
 * Created by guokun on 2019/11/11.
 */
public class PullableLayout extends ViewGroup {
    private static final String TAG = "PullableLayout";

    // ViewGroup的内容高度(不包括header与footer的高度)
    private int mLayoutContentHeight;
    private int mTouchSlop;

    private View mHeader;
    private View mFooter;
    private float mlastY;
    private int effectiveScrollY;
    private TextView tvPullHeader;
    private ProgressBar pbPullHeader;
    private TextView tvPullFooter;
    private OverScroller mScroller;

    public PullableLayout(Context context) {
        this(context, null);
    }

    public PullableLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeader = LayoutInflater.from(context).inflate(R.layout.header, null);
        tvPullHeader = mHeader.findViewById(R.id.tv_pullable_header);
        pbPullHeader = mHeader.findViewById(R.id.pb_pullable_header);

        mFooter = LayoutInflater.from(context).inflate(R.layout.footer, null);
        tvPullFooter = mFooter.findViewById(R.id.tv_pullable_footer);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mScroller = new OverScroller(context);
        effectiveScrollY = Util.dip2px(context, 60);
    }

    @Override
    protected void onFinishInflate() {
        Log.i(TAG, "<onFinishInflate>");
        super.onFinishInflate();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mHeader.setLayoutParams(params);
        mFooter.setLayoutParams(params);
        addView(mHeader);
        addView(mFooter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.i(TAG, "<onMeasure>");

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, "<onLayout>");

        mLayoutContentHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (child == mHeader) {
                child.layout(0, 0 - child.getMeasuredHeight(), child.getMeasuredWidth(), 0);
            } else if (child == mFooter) {
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(), mLayoutContentHeight + child.getMeasuredHeight());
            } else {
                child.layout(0, mLayoutContentHeight, child.getMeasuredWidth(), mLayoutContentHeight + child.getMeasuredHeight());
                mLayoutContentHeight += child.getMeasuredHeight();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "<onInterceptTouchEvent>");

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
//            invalidate();
//            postInvalidate();
        }
        postInvalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "<onTouchEvent>");
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mlastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float y = event.getY();
                final int yDiff = (int) Math.abs(y - mlastY);
                if (yDiff > mTouchSlop) {
                    if (mlastY - y < 0) {
                        //下拉刷新
                        if (Math.abs(getScrollY()) <= mHeader.getMeasuredHeight() / 2) {
                            scrollBy(0, (int) (mlastY - y));
                            mlastY = y;
                            if (Math.abs(getScrollY()) > effectiveScrollY) {
                                tvPullHeader.setText("松开刷新");
                            }
                        }
                    } else {
                        //上拉加载更多
                        if (Math.abs(getScrollY()) <= mFooter.getMeasuredHeight() / 2) {
                            scrollBy(0, (int) (mlastY - y));
                            mlastY = y;
                            if (Math.abs(getScrollY()) > effectiveScrollY) {
                                tvPullFooter.setText("松开刷新");
                            }
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(getScrollY()) < effectiveScrollY) {
                    mScroller.startScroll(0, getScrollY(), 0, -getScrollY());
//                    scrollBy(0, -getScrollY());
                } else {
                    mScroller.startScroll(0, getScrollY(), 0, -(getScrollY() + effectiveScrollY));
//                    scrollBy(0, -(getScrollY() + effectiveScrollY));
                    tvPullHeader.setVisibility(View.GONE);
                    pbPullHeader.setVisibility(View.VISIBLE);
                }
                break;
        }

        return true;
    }
}
