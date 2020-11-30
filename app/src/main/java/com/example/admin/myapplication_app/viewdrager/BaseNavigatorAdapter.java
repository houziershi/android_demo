package com.example.admin.myapplication_app.viewdrager;

import android.content.Context;
import android.graphics.Color;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.viewpager.widget.ViewPager;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

//import bubei.tingshu.commonlib.utils.StringUtils;


public abstract class BaseNavigatorAdapter extends CommonNavigatorAdapter {
    protected String[] mTitles;
    protected ViewPager mViewPager;
    protected static final String NORMAL_COLOR = "#333332";
    protected static final String SELECT_COLOR = "#f39c11";
    protected String mNormalColor = NORMAL_COLOR;
    protected String mSelectColor = SELECT_COLOR;

    protected int radios = 3;//默认全局3dp圆角
    public BaseNavigatorAdapter(String[] titles, ViewPager viewPager) {
        mTitles = titles;
        mViewPager = viewPager;
    }

    public void setDataList(String[] titles){
        this.mTitles = titles;
        notifyDataSetChanged();
    }

//    public void setThemeColor(String normalColor, String selectColor) {
//        this.mNormalColor = StringUtils.isEmptyOrNull(normalColor) ? NORMAL_COLOR : normalColor;
//        this.mSelectColor = StringUtils.isEmptyOrNull(selectColor) ? SELECT_COLOR : selectColor;
//    }

    public void setRadios(int radios){
        this.radios = radios;
    }
    @Override
    public int getCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, int i) {
        return getPagerTitleView(context, i);
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setLineHeight(UIUtil.dip2px(context, 3));
        indicator.setLineWidth(UIUtil.dip2px(context, 19));
        indicator.setStartInterpolator(new AccelerateInterpolator());
        indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
        if(radios > 0){
            indicator.setRoundRadius(UIUtil.dip2px(context, radios));
        }
        try {
            indicator.setColors(Color.parseColor(mSelectColor));
        } catch (Exception e) {
            indicator.setColors(Color.parseColor(SELECT_COLOR));
            e.printStackTrace();
        }
        return indicator;
    }

    public abstract IPagerTitleView getPagerTitleView(Context context, int i);
}
