package com.example.admin.myapplication_app.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.admin.myapplication_app.R;

/**
 * Discription:
 * Created by guokun on 2019/4/24.
 */
public class CustomGroupView extends LinearLayout {

    public CustomGroupView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.custom_group,this);
    }

    public CustomGroupView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomGroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.custom_group,this);
    }
}
