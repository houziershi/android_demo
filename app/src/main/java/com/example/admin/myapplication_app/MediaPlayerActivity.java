package com.example.admin.myapplication_app;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.admin.myapplication_app.viewdrager.BottomSheetUtils;
import com.example.admin.myapplication_app.viewdrager.PagerAdapter;
import com.example.admin.myapplication_app.viewdrager.SimpleNavigatorAdapter;
import com.example.admin.myapplication_app.viewdrager.SwipeBackLayout;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;

/**
 * Discription:
 * Created by guokun on 2020/11/23.
 */
public class MediaPlayerActivity extends AppCompatActivity {

    private ViewPager bottomSheetViewPager;
//    private TabLayout bottomSheetTabLayout;
    private MagicIndicator mMagicIndicator;
    private String[] titles = new String[]{"评论", "目录", "推荐", "阅读"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_layout);
        bottomSheetViewPager = findViewById(R.id.bottom_sheet_viewpager);
//        bottomSheetTabLayout = findViewById(R.id.bottom_sheet_tabs);
        mMagicIndicator = findViewById(R.id.indicator);
        setupBottomSheet();
        initMagicIndicator();
        SwipeBackLayout mSwipeBackLayout = new SwipeBackLayout(this);
        mSwipeBackLayout.attachToActivity(this);
        mSwipeBackLayout.setDirectionMode(SwipeBackLayout.FROM_TOP);
    }

    private void setupBottomSheet() {
        final PagerAdapter sectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager(), this, PagerAdapter.TabItem.NESTED_SCROLL, PagerAdapter.TabItem.VIEW_PAGER, PagerAdapter.TabItem.RECYCLER, PagerAdapter.TabItem.RECYCLER);
        bottomSheetViewPager.setOffscreenPageLimit(1);
        bottomSheetViewPager.setAdapter(sectionsPagerAdapter);
//        bottomSheetTabLayout.setupWithViewPager(bottomSheetViewPager);
        BottomSheetUtils.setupViewPager(bottomSheetViewPager);
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setScrollPivotX(0.65f);
        CommonNavigatorAdapter navigatorAdapter = getCommonNavigatorAdapter(titles, bottomSheetViewPager);
        commonNavigator.setAdapter(navigatorAdapter);
        commonNavigator.setAdjustMode(false);
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, bottomSheetViewPager);
    }

    private CommonNavigatorAdapter getCommonNavigatorAdapter(String[] titles, ViewPager bottomSheetViewPager) {
        SimpleNavigatorAdapter navigatorAdapter = new SimpleNavigatorAdapter(titles, bottomSheetViewPager);
        navigatorAdapter.setPaddingLeftRight(33, 33);
        navigatorAdapter.setTextSize(16);
        navigatorAdapter.setNormalColor(Color.parseColor("#333333"));
        return navigatorAdapter;
    }

    @Override
    public void finish() {
        super.finish();
    }
}
