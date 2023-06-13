package com.example.zetutorial.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

class ResourceProviderImpl(private val context: Context): ResourceProvider {

    override fun getString(stringRes: Int): String {
        return context.getString(stringRes)
    }

    override fun getDrawable(drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableRes)
    }
}