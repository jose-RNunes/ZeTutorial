package com.example.zetutorial.domain.model

import java.io.IOException

data class DefaultException(
    val code: String = String(),
    override val message: String? = String()
): IOException(message)
