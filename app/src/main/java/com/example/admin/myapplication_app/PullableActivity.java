package com.example.admin.myapplication_app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import me.hwang.library.widgit.SmartRefreshLayout;

public class PullableActivity extends AppCompatActivity {
    private SmartRefreshLayout mLayout;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullable);
        mLayout = findViewById(R.id.smart_refresh_layout);
        mLayout.setOnRefreshListener(new SmartRefreshLayout.onRefreshListener() {
            @Override
            public void onRefresh() {
                /*
                 * do what you want do
                 */
                mLayout.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                /*
                 * do what you want do
                 */
                mLayout.stopLoadMore();
            }
        });
        view.getClass();
    }

}
