package com.example.admin.myapplication_app.progressbar

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider

/**
 * Discription:
 * Created by guokun on 2019/5/28.
 */
class MyTextViewK : AppCompatTextView {
    private val BORDER = 2
    private var mPaint2: Paint = Paint()
    private var mPaint1: Paint = Paint()
    private var mPaint: Paint = paint
    private var mViewWidth = 0
    private var mTranslate = 0
    private var mGradientMatrix: Matrix? = null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initPaint()
    }

    private fun initPaint() {
        mPaint1.color = Color.RED
        mPaint1.style = Paint.Style.FILL

        mPaint2.color = Color.GREEN
        mPaint2.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setPadding(paddingLeft + BORDER, paddingTop + BORDER, paddingRight + BORDER, paddingBottom + BORDER)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mViewWidth == 0) {
            mViewWidth = measuredWidth

            if (mViewWidth > 0) {
                mPaint.shader = LinearGradient(
                        0f,
                        0f,
                        mViewWidth.toFloat(),
                        0f,
                        intArrayOf(Color.BLUE, -0x1, Color.BLUE),
                        null,
                        Shader.TileMode.CLAMP
                )
                mGradientMatrix = Matrix()
            }
        }

    }
}