package com.example.admin.myapplication_app.viewdrager;

import android.content.Context;
import android.util.TypedValue;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

/**
 * 作者：hmm
 * 时间：2018/1/22
 * 描述：特殊需求
 */

public class MySimplePagerTitleView extends SimplePagerTitleView {
    protected boolean isSelectedBold;
    protected float mSelectedSize;
    protected float mNormalSize;

    public MySimplePagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        super.onSelected(index, totalCount);
        if(isSelectedBold){
            getPaint().setFakeBoldText(true);
        }
        if (mSelectedSize > 0) {
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, mSelectedSize);
        }
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        super.onDeselected(index, totalCount);
        if(isSelectedBold){
            getPaint().setFakeBoldText(false);
        }
        if (mNormalSize > 0) {
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, mNormalSize);
        }
    }

    public void setFontBold(boolean isSelectedBold){
        this.isSelectedBold = isSelectedBold;
    }
    
    public void setSelectedSize(float selectedSize) {
        this.mSelectedSize = selectedSize;
    }
    
    public void setNormalSize(float normalSize) {
        this.mNormalSize = normalSize;
    }
}
