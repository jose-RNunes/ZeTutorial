package com.example.zetutorial.data.remote

import com.example.zetutorial.R
import com.example.zetutorial.data.remote.response.NetworkErrorResponse
import com.example.zetutorial.domain.model.DefaultException
import com.example.zetutorial.utils.ResourceProvider
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorInterceptorImpl @Inject constructor(
    private val resourceProvider: ResourceProvider,
    private val moshi: Moshi
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val response: Response
        try {
            response = chain.proceed(request)
        } catch (exception: Exception) {
           val error =  when(exception) {
                is HttpException -> tryGetErrorBody(exception.response()?.errorBody())
                is UnknownHostException -> DefaultException(
                    message = resourceProvider.getString(R.string.network_error)
                )
               is IOException -> DefaultException(
                   message = resourceProvider.getString(R.string.unexpected_error)
               )
               else -> DefaultException(message = resourceProvider.getString(R.string.unexpected_error))
            }
            throw error
        }

        return response
    }

    private fun tryGetErrorBody(responseBody: ResponseBody?): DefaultException {
        val defaultMessage = resourceProvider.getString(R.string.unexpected_error)
        return if(responseBody == null) {
            DefaultException(message = defaultMessage)
        } else {
            val errorResponse = getErrorJson(responseBody)
            DefaultException(code = errorResponse?.code.orEmpty(), errorResponse?.message ?: defaultMessage)
        }
    }

    private fun getErrorJson(errorBody: ResponseBody): NetworkErrorResponse? {
        val adapter = moshi.adapter(NetworkErrorResponse::class.java)
        return runCatching { adapter.fromJson(errorBody.string()) }.getOrNull()
    }
}