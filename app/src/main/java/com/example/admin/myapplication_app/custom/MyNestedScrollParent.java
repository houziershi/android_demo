package com.example.admin.myapplication_app.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParentHelper;

import com.example.admin.myapplication_app.R;

/**
 * Discription:
 * Created by guokun on 2020/1/16.
 */
public class MyNestedScrollParent extends LinearLayout implements NestedScrollingParent2 {
    private ImageView img;
    private TextView tv;
    private MyNestedScrollChild myNestedScrollChild;
    private final NestedScrollingParentHelper nestedScrollingParentHelper;
    private int imgHeight;
    private int tvHeight;

    public MyNestedScrollParent(Context context) {
        this(context, null);
    }

    public MyNestedScrollParent(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyNestedScrollParent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv = findViewById(R.id.tv_parent);
        img = findViewById(R.id.iv_parent);
        myNestedScrollChild = findViewById(R.id.my_nested_child);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (imgHeight <= 0) {
                    imgHeight = img.getMeasuredHeight();
                }

                if (tvHeight <= 0) {
                    tvHeight = tv.getMeasuredHeight();
                }
            }
        });
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        if (target instanceof MyNestedScrollChild) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        nestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        nestedScrollingParentHelper.onStopNestedScroll(target, type);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        if (showImg(dy) || hideImg(dy)) {
            scrollBy(0, -dy);
            consumed[1] = dy;
        }
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    private boolean showImg(int dy) {
        if (dy > 0) {
            if (getScrollY() > 0 && myNestedScrollChild.getScrollY() == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean hideImg(int dy) {
        if (dy < 0) {
            if (getScrollY() < imgHeight) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > imgHeight) {
            y = imgHeight;
        }
        super.scrollTo(x, y);
    }
}
