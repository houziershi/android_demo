package com.example.admin.myapplication_app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.admin.myapplication_app.custom.BulletViewParent;
import com.example.admin.myapplication_app.custom.CustomBulletView;
import com.example.admin.myapplication_app.progressbar.MyLineIndicator;

import java.util.Calendar;

/**
 * Discription:
 * Created by guokun on 2018/9/4.
 */
public class MyCustomActivity extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    private VelocityTracker mVelocityTracker = null;

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;
    private LinearLayout container;
    private BulletViewParent bulletViewParent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycustom_activity);
        ((MyLineIndicator) findViewById(R.id.my_line_indicator)).starAnimator();
        container = findViewById(R.id.container);
        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);

        CustomBulletView customBulletView = new CustomBulletView(this);
        customBulletView.setLayoutParams(new LinearLayout.LayoutParams(Util.dip2px(this, 200), Util.dip2px(this, 40)));
        container.addView(customBulletView);

        bulletViewParent = findViewById(R.id.bullet_parent);

        CustomBulletView customBullet = new CustomBulletView(this);
        customBullet.setLayoutParams(new FrameLayout.LayoutParams(Util.dip2px(this, 200), Util.dip2px(this, 40)));
        bulletViewParent.addView(customBullet);

    }

    @Override
    protected void onResume() {
        super.onResume();
        bulletViewParent.childViewAutoMove();
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
