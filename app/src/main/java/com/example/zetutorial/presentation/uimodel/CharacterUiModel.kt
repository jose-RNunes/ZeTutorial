package com.example.zetutorial.presentation.uimodel

import android.graphics.drawable.Drawable

data class CharacterUiModel(
    val id: String,
    val name: String,
    val descriptionTitle: String,
    val description: String,
    val hasDescription: Boolean,
    val thumbnailUrl: String,
    val placeholder: Drawable?
)
