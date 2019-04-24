package com.example.admin.myapplication_app.custom;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Discription:
 *
 * @author guokun
 * @date 2019/4/23
 */
public class BulletViewParent extends FrameLayout {

    public final BulletHandler mHandler = new BulletHandler(this);
    private final int displayLines = 4;
    private final long animationTime = 6 * 1000L; //动画时间
    private int lastLine;
    private int barrageViewHeight;
    private int barrageViewWidth;
    private LinearInterpolator linearInterpolator;
    private boolean isStart;
    private int currentIndex;
    private boolean isRandomDistanTime = true; //是否随机间隔时间
    private long distanceTime = 3 * 1000L; //两条弹幕间隔时间
    private List<View> customBulletViewList;
    private boolean isRepeat = true;//是否循环显示


    public BulletViewParent(@NonNull Context context) {
        super(context);
    }

    public BulletViewParent(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BulletViewParent(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        barrageViewWidth = getWidth();
        barrageViewHeight = getHeight();
    }


    public void setData(List<View> list) {
        customBulletViewList = list;
    }

    public void start() {
        isStart = true;
        mHandler.sendEmptyMessage(BulletHandler.CODE_START);
    }

    public void onResume() {
        if (!isStart) {
            isStart = true;
            mHandler.sendEmptyMessage(BulletHandler.CODE_NEXT);
        }
    }

    public void onPause() {
        isStart = false;
        mHandler.removeMessages(BulletHandler.CODE_NEXT);
    }

    public void onDestroy() {
        cancel();
    }

    public void cancel() {
        isStart = false;
        currentIndex = 0;
        if (customBulletViewList != null) {
            customBulletViewList.clear();
        }
        removeAllViews();
        mHandler.removeMessages(BulletHandler.CODE_NEXT);
    }


    private void addBulletView(final View view) {
        view.setY(getItemRamdomY());
        view.measure(0, 0);
        int itemViewWidth = view.getMeasuredWidth();
        view.setX(this.barrageViewWidth);
        addView(view);

        if (linearInterpolator == null) {
            linearInterpolator = new LinearInterpolator();
        }
        final ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationX", -itemViewWidth);
        anim.setDuration(animationTime);
        anim.setInterpolator(linearInterpolator);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                anim.cancel();
                view.clearAnimation();
                removeView(view);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
    }

    /**
     * 获得随机的Y轴的值
     */
    private float getItemRamdomY() {
        int currentY;

        //随机选择弹幕出现的行数位置，跟上一条位置不同行
        int randomLine = lastLine;
        while (randomLine == lastLine) {
            randomLine = (int) (Math.random() * displayLines + 1);
        }

        //当前itemView y值
        currentY = barrageViewHeight / displayLines * (randomLine - 1);
        lastLine = randomLine;
        return currentY;
    }

    private static class BulletHandler extends Handler {
        public static final int CODE_START = 1000;
        public static final int CODE_NEXT = 1001;
        public static final int CODE_END = 1002;
        private final WeakReference<BulletViewParent> bulletViewParentWeakReference;


        public BulletHandler(BulletViewParent bulletViewParent) {
            this.bulletViewParentWeakReference = new WeakReference<>(bulletViewParent);
        }

        @Override
        public void handleMessage(Message msg) {
            BulletViewParent bulletViewParent = bulletViewParentWeakReference.get();
            if (bulletViewParent != null) {
                switch (msg.what) {
                    case CODE_START:
                        bulletViewParent.mHandler.sendEmptyMessage(CODE_NEXT);
                        break;
                    case CODE_NEXT:
                        if (bulletViewParent.isStart && bulletViewParent.customBulletViewList != null && bulletViewParent.currentIndex < bulletViewParent.customBulletViewList.size()) {
                            View item = bulletViewParent.customBulletViewList.get(bulletViewParent.currentIndex);
                            bulletViewParent.addBulletView(item);
                            bulletViewParent.currentIndex++;
                            long randomSleepTime;
                            if (bulletViewParent.isRandomDistanTime) {
                                randomSleepTime = (long) (Math.random() * 5 + 3) * 200L;
                            } else {
                                randomSleepTime = bulletViewParent.distanceTime;
                            }
                            bulletViewParent.mHandler.sendEmptyMessageDelayed(CODE_NEXT, randomSleepTime);
                        } else {
                            bulletViewParent.mHandler.sendEmptyMessage(CODE_END);
                        }
                        break;
                    case CODE_END:
                        Log.d("hgk", "CODE_END");
                        if (bulletViewParent.isRepeat) {
                            if (bulletViewParent.currentIndex != 0) {
                                bulletViewParent.currentIndex = 0;
                                bulletViewParent.mHandler.sendEmptyMessage(CODE_NEXT);
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

}
