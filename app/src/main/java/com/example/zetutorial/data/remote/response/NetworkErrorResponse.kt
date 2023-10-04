package com.example.zetutorial.data.remote.response

import java.io.IOException

data class NetworkErrorResponse(val code: String, override val message: String): IOException(message)