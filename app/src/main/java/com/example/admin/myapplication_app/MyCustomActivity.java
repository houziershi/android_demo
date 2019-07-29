package com.example.admin.myapplication_app;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.VelocityTrackerCompat;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication_app.custom.BulletViewParent;
import com.example.admin.myapplication_app.custom.CustomGroupView;
import com.example.admin.myapplication_app.customview.FoldTextView;
import com.example.admin.myapplication_app.customview.SpannableFoldTextView;
import com.example.admin.myapplication_app.progressbar.MyLineIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Discription:
 * Created by guokun on 2018/9/4.
 */
public class MyCustomActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private static final String DEBUG_TAG = "Gestures";
    private VelocityTracker mVelocityTracker = null;
    private GestureDetectorCompat mDetector;
    private LinearLayout container;
    private BulletViewParent bulletViewParent;
    private SpannableFoldTextView spanTextView;
    private FoldTextView foldTextView;
    private TextView gradientTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycustom_activity);
        ((MyLineIndicator) findViewById(R.id.my_line_indicator)).starAnimator();
        container = findViewById(R.id.container);
        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);

       /* CustomBulletView customBulletView = new CustomBulletView(this);
        customBulletView.setLayoutParams(new LinearLayout.LayoutParams(Util.dip2px(this, 200), Util.dip2px(this, 40)));
        container.addView(customBulletView);*/

        bulletViewParent = findViewById(R.id.bullet_parent);
        List<View> customBulletViews = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CustomGroupView customGroupView = new CustomGroupView(this);
            customGroupView.setLayoutParams(new FrameLayout.LayoutParams(Util.dip2px(this, 70), Util.dip2px(this, 20)));
            customBulletViews.add(customGroupView);
        }
        bulletViewParent.setData(customBulletViews);
        bulletViewParent.start();

        spanTextView = findViewById(R.id.text);
        spanTextView.setText("111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送");
        spanTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyCustomActivity.this, "textView点击事件", Toast.LENGTH_SHORT).show();
            }
        });

        foldTextView = findViewById(R.id.text2);
        foldTextView.setText("111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送111111123阿斯顿发阿斯顿发送到大。厦法定阿萨【德法师打发斯蒂芬撒地】方阿萨德法师打发斯问问蒂芬撒地方阿萨德法师打发斯蒂。芬撒地方发送到发送到发送到发送到发送到发送，到发送到发送到发送到，发送");
        foldTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyCustomActivity.this, "textView点击事件", Toast.LENGTH_SHORT).show();
            }
        });

        gradientTv = findViewById(R.id.gradient_tv);
        final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(gradientTv, "scaleX", 0.5f, 1f);
        objectAnimator.setDuration(2000);
        objectAnimator.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        bulletViewParent.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bulletViewParent.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bulletViewParent.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
//        return super.onTouchEvent(event);
        int index = event.getActionIndex();
        int action = event.getActionMasked();
        int pointerId = event.getPointerId(index);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                } else {
                    mVelocityTracker.clear();
                }

                mVelocityTracker.addMovement(event);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.d(DEBUG_TAG, " ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.addMovement(event);
                mVelocityTracker.computeCurrentVelocity(1000);
                Log.d(DEBUG_TAG, "X velocity: " +
                        VelocityTrackerCompat.getXVelocity(mVelocityTracker,
                                pointerId));
                Log.d(DEBUG_TAG, "Y velocity: " +
                        VelocityTrackerCompat.getYVelocity(mVelocityTracker,
                                pointerId));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
//                mVelocityTracker.recycle();
            default:
                break;
        }

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + e.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + e.toString());
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d(DEBUG_TAG, "onDown: " + e.toString());
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d(DEBUG_TAG, "onShowPress: " + e.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + e.toString());
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString() + "--------" + e2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d(DEBUG_TAG, "onLongPress: " + e.toString());
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + e1.toString() + e2.toString());
        return true;
    }
}
