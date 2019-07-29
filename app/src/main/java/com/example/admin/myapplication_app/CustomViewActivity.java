package com.example.admin.myapplication_app;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.myapplication_app.custom.RoundRectangleLayoutWithClipPath;


public class CustomViewActivity extends AppCompatActivity {

    RoundRectangleLayoutWithClipPath parentView;
    RoundRectangleLayoutWithClipPath view;
    TextView tv_emoji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        parentView = findViewById(R.id.clip_parent_view);
        view = findViewById(R.id.clip_view);
        tv_emoji = findViewById(R.id.tv_emoji);

        view.post(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(parentView,"alpha",0f,1f);
                objectAnimator.setDuration(5000);
                objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (Float) animation.getAnimatedValue();
                        parentView.setRate(value);
                        view.setRate(value);
                    }
                });
                objectAnimator.start();
            }
        });

        int adType = 2;
        switch (adType) {
            case 1:
                break;
            default:
                return;
        }
        Log.i("CustomViewActivity===","adtype="+adType);

        tv_emoji.setText("\uD83D");
    }
}
