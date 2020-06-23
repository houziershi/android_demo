package com.example.admin.myapplication_app.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.admin.myapplication_app.R
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Discription: 绘制黑色三角形，常用于首次开发某新功能首次提示 圆框上面+三角形
 * Created by guokun on 2020/6/23.
 */
class TriangleView : View {
    private val paint = Paint()
    private val path = Path()
    private var triangleHeight: Float = 0.0f
    private var width: Float = 0.0f
    //false 三角形朝上， true 三角形朝下
    private var triangel: Boolean = false

    constructor(mContext: Context) : this(mContext, null)

    constructor(mContext: Context, attrs: AttributeSet?) : this(mContext, attrs!!, 0)

    constructor(mContext: Context, attrs: AttributeSet, defStyleAttr: Int) : super(mContext, attrs, defStyleAttr) {
        init(mContext, attrs)
    }

    private fun init(mContext: Context, attrs: AttributeSet) {
        val array = mContext.obtainStyledAttributes(attrs, R.styleable.TriangleView)
        triangel = array.getBoolean(R.styleable.TriangleView_triangle, false)
        paint.color = Color.BLACK
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 1f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = measuredWidth.toFloat()
        triangleHeight = getHeight(width.toDouble())
    }

    private fun getHeight(width: Double): Float {
        return sqrt((width.pow(2.0) - (width / 2).pow(2.0))).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return
        path.reset()
        if (triangel) {
            path.moveTo(0f, 0f)
            path.lineTo(width, 0f)
            path.lineTo(width / 2f, triangleHeight)
        } else {
            path.moveTo(0f, width)
            path.lineTo(width / 2f, width - triangleHeight)
            path.lineTo(width, width)
        }
        path.close()
        canvas.drawPath(path, paint)
    }
}