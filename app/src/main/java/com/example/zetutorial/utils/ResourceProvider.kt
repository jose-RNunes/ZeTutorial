package com.example.zetutorial.utils

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes stringRes: Int): String

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable?
}