package com.example.zetutorial.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.zetutorial.R
import com.example.zetutorial.extensions.px
import kotlin.math.absoluteValue

class FootballFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val grassPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val linePaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 4.px
        color = Color.WHITE
    }

    private val linesPath = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawGrass(canvas)
            drawLines(canvas)
            drawCorners(canvas)
            drawMidField(canvas)
            drawTopGoalKeeperArea(canvas)
            drawBottomGoalKeeperArea(canvas)
        }
    }

    private fun drawGrass(canvas: Canvas) {
        val grassRectHeight = (height / 7).absoluteValue
        (0..7).forEach { value ->
            grassPaint.color = getGrassColor(value)
            val top = (value * grassRectHeight).toFloat()
            canvas.drawRect(0f, top, width.toFloat(), top + grassRectHeight, grassPaint)
        }
    }

    private fun drawLines(canvas: Canvas) {
        val padding = 25.px
        linesPath.moveTo(padding, padding)
        linesPath.lineTo(padding, height - padding)
        linesPath.lineTo(width - padding, height - padding)
        linesPath.lineTo(width - padding, padding)
        linesPath.close()
        canvas.drawPath(linesPath, linePaint)
    }

    private fun drawCorners(canvas: Canvas) {
        val padding = 25.px
        val cornersSize = 40.px
        val topLeftRect = RectF(
            padding / 2,
            padding / 2,
            cornersSize,
            cornersSize
        )
        canvas.drawArc(topLeftRect, 0f, 90f, false, linePaint)

        val topRightRect = RectF(
            width - cornersSize,
            padding / 2,
            width - padding / 2,
            cornersSize
        )
        canvas.drawArc(topRightRect, 90f, 90f, false, linePaint)

        val bottomLeftRect = RectF(
            padding / 2,
            height - cornersSize,
            cornersSize,
            height - padding / 2
        )
        canvas.drawArc(bottomLeftRect, 270f, 90f, false, linePaint)

        val bottomRightRect = RectF(
            width - cornersSize,
            height - cornersSize,
            width - padding / 2,
            height - padding / 2
        )
        canvas.drawArc(bottomRightRect, 180f, 90f, false, linePaint)
    }

    private fun drawMidField(canvas: Canvas) {
        val rectF = getDefaultRectF()
        canvas.drawLine(getDefaultPadding(), rectF.centerY(), width - getDefaultPadding(), rectF.centerY(), linePaint)
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), 10f, linePaint)
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), 150f, linePaint)
    }

    private fun drawTopGoalKeeperArea(canvas: Canvas) {
        val rectTop = RectF(
            getDefaultRectF().centerX() - 80.px,
            getDefaultPadding(),
            getDefaultRectF().centerX() + 80.px,
            80.px
        )
        canvas.drawRect(rectTop, linePaint)

        rectTop.left = rectTop.left - 20.px
        rectTop.right = rectTop.right + 20.px
        rectTop.bottom = rectTop.bottom + 20.px

        canvas.drawRect(rectTop, linePaint)

        val topCircle = RectF(
            getDefaultRectF().centerX() - 60.px,
            80.px,
            getDefaultRectF().centerX() + 60.px,
            120.px
        )
        canvas.drawArc(topCircle, 0f, 180f, false, linePaint)
    }

    private fun drawBottomGoalKeeperArea(canvas: Canvas) {
        val rectBottom = RectF(
            getDefaultRectF().centerX() - 80.px,
            height - 80.px,
            getDefaultRectF().centerX() + 80.px,
            height - getDefaultPadding()
        )
        canvas.drawRect(rectBottom, linePaint)

        rectBottom.left = rectBottom.left - 20.px
        rectBottom.right = rectBottom.right + 20.px
        rectBottom.top = rectBottom.top  - 20.px

        canvas.drawRect(rectBottom, linePaint)

        val bottomCircle = RectF(
            getDefaultRectF().centerX() - 60.px,
            height - 120.px,
            getDefaultRectF().centerX() + 60.px,
            height - 80.px
        )
        canvas.drawArc(bottomCircle, 180f, 180f, false, linePaint)
    }

    private fun getGrassColor(index: Int): Int {
        val colorRes = if ((index % 2) == 0) {
            R.color.green_700
        } else {
            R.color.green_500
        }
        return context.getColor(colorRes)
    }

    private fun getDefaultRectF() = RectF(
        getDefaultPadding(),
        getDefaultPadding(),
        width - getDefaultPadding(),
        height - getDefaultPadding()
    )

    private fun getDefaultPadding(): Float = DEFAULT_PADDING.px

    private companion object {
        const val DEFAULT_PADDING = 25
    }
}