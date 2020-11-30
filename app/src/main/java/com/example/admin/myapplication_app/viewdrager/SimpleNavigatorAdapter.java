package com.example.admin.myapplication_app.viewdrager;


import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class SimpleNavigatorAdapter extends BaseNavigatorAdapter {

    private final static int INVALID_INT = -1;

    private List<SimplePagerTitleView> mPagerTitleViewList;
    protected TitleClickListener mTitleClickListener;

    private float TEXT_SIZE = 17;
    private float SELECT_TEXT_SIZE;
    private int NORMAL_COLOR = INVALID_INT;
    private int SELECT_COLOR = INVALID_INT;
    private int paddingLeft = -1;
    private int paddingRight = -1;

    protected boolean notShowIndicator;

    public void setNotShowIndicator(boolean notShowIndicator) {
        this.notShowIndicator = notShowIndicator;
    }
    
    public interface TitleClickListener {
        void onTitleClick(int pos);
    }
    
    public SimpleNavigatorAdapter(String[] titles, ViewPager viewPager) {
        super(titles, viewPager);
        mPagerTitleViewList = new ArrayList<>();
    }
    @Override
    public IPagerTitleView getPagerTitleView(Context context, final int i) {
        MySimplePagerTitleView simplePagerTitleView = new MySimplePagerTitleView(context);
        simplePagerTitleView.setText(mTitles[i]);
        simplePagerTitleView.setFontBold(true);
        simplePagerTitleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE);
        simplePagerTitleView.setNormalSize(TEXT_SIZE);
        simplePagerTitleView.setSelectedSize(SELECT_TEXT_SIZE);
        simplePagerTitleView.setNormalColor(NORMAL_COLOR == INVALID_INT ? Color.parseColor("#333332") : NORMAL_COLOR);
        simplePagerTitleView.setSelectedColor(SELECT_COLOR == INVALID_INT ? Color.parseColor("#f39c11") : SELECT_COLOR);
        int leftPadding = paddingLeft >= 0 ? paddingLeft : simplePagerTitleView.getPaddingLeft();
        int rightPadding = paddingRight >= 0 ? paddingRight : simplePagerTitleView.getPaddingRight();
        simplePagerTitleView.setPadding(leftPadding, simplePagerTitleView.getPaddingTop(), rightPadding, simplePagerTitleView.getPaddingBottom());
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager != null) {
                    mViewPager.setCurrentItem(i, false);
                }
                if (mTitleClickListener != null) {
                    mTitleClickListener.onTitleClick(i);
                }
            }
        });
        mPagerTitleViewList.add(simplePagerTitleView);
        return simplePagerTitleView;
    }


    public void updateTitle(int i, CharSequence title) {
        if (i > 0 && i < mPagerTitleViewList.size()) {
            mPagerTitleViewList.get(i).setText(title);
            notifyDataSetInvalidated();
        }
    }

    public void setTextSize(float textSize) {
        TEXT_SIZE = textSize;
    }
    
    public void setSelectTextSize(float selectTextSize) {
        SELECT_TEXT_SIZE = selectTextSize;
    }
    
    public void setNormalColor(int color) {
        NORMAL_COLOR = color;
    }

    public void setSelectColor(int color) {
        SELECT_COLOR = color;
    }

    /**
     * 设置item左右间距
     * @param paddingLeft px
     * @param paddingRight px
     */
    public void setPaddingLeftRight(int paddingLeft, int paddingRight) {
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
    }

    public void setTitleClickListener(TitleClickListener titleClickListener) {
        this.mTitleClickListener = titleClickListener;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        if (notShowIndicator) {
            return null;
        } else {
            return super.getIndicator(context);
        }
    }
}
