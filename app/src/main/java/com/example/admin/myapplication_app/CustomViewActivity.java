package com.example.admin.myapplication_app;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.admin.myapplication_app.custom.RoundRectangleLayoutWithClipPath;


public class CustomViewActivity extends AppCompatActivity {

    RoundRectangleLayoutWithClipPath parentView;
    RoundRectangleLayoutWithClipPath view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);

        parentView = findViewById(R.id.clip_parent_view);
        view = findViewById(R.id.clip_view);

        ((AnimationDrawable) ((ImageView)findViewById(R.id.star)).getDrawable()).start();

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

        Path path = new Path();

    }
}
