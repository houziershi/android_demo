package com.example.admin.myapplication_app.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.admin.myapplication_app.R;

/**
 * Discription:
 * Created by guokun on 2019/11/11.
 */
public class PullableLayout extends ViewGroup {

    // ViewGroup的内容高度(不包括header与footer的高度)
    private int mLayoutContentHeight;

    private View mHeader;
    private View mFooter;

    public PullableLayout(Context context) {
        this(context, null);
    }

    public PullableLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHeader = LayoutInflater.from(context).inflate(R.layout.header, null);
        mFooter = LayoutInflater.from(context).inflate(R.layout.footer, null);
    }

    @Override
    protected void onFinishInflate() {
        Log.i("hgk", " <onFinishInflate>........");
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
        Log.i("hgk", " <onMeasure>........");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("hgk","<onLayout>");
        mLayoutContentHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            Log.i("hgk", " <onLayout>........======" + child.getClass().getSimpleName());
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
}
