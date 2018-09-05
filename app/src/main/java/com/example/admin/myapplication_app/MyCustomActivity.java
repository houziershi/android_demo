package com.example.admin.myapplication_app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.myapplication_app.progressbar.MyLineIndicator;

/**
 * Discription:
 * Created by guokun on 2018/9/4.
 */
public class MyCustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycustom_activity);
        ((MyLineIndicator) findViewById(R.id.my_line_indicator)).starAnimator();
    }
}
