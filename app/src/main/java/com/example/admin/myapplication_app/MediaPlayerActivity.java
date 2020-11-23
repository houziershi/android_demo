package com.example.admin.myapplication_app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.admin.myapplication_app.viewdrager.BottomSheetUtils;
import com.example.admin.myapplication_app.viewdrager.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * Discription:
 * Created by guokun on 2020/11/23.
 */
public class MediaPlayerActivity extends AppCompatActivity {

    private ViewPager bottomSheetViewPager;
    private TabLayout bottomSheetTabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_layout);
        bottomSheetViewPager = findViewById(R.id.bottom_sheet_viewpager);
        bottomSheetTabLayout = findViewById(R.id.bottom_sheet_tabs);

        setupBottomSheet();
    }

    private void setupBottomSheet() {
        final PagerAdapter sectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager(), this, PagerAdapter.TabItem.NESTED_SCROLL, PagerAdapter.TabItem.VIEW_PAGER, PagerAdapter.TabItem.RECYCLER, PagerAdapter.TabItem.RECYCLER);
        bottomSheetViewPager.setOffscreenPageLimit(1);
        bottomSheetViewPager.setAdapter(sectionsPagerAdapter);
        bottomSheetTabLayout.setupWithViewPager(bottomSheetViewPager);
        BottomSheetUtils.setupViewPager(bottomSheetViewPager);
    }
}
