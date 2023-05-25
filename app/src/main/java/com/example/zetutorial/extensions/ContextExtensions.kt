package com.example.zetutorial.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getDimen(@DimenRes dimen: Int): Float = resources.getDimension(dimen)

fun Context.getCompactColor(@ColorRes color: Int): Int = ContextCompat.getColor(this, color)

fun Context.getCompactDrawable(@DrawableRes drawable: Int): Drawable? = ContextCompat.getDrawable(this, drawable)

val Int.dp: Float
    get() = (this / Resources.getSystem().displayMetrics.density)

val Int.px: Float
    get() = (this * Resources.getSystem().displayMetrics.density)
