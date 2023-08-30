package com.example.zetutorial.data.remote.api

import com.example.zetutorial.BuildConfig
import com.example.zetutorial.di.qualifiers.PrivateApiQualifier
import com.example.zetutorial.di.qualifiers.PublicApiQualifier
import com.example.zetutorial.utils.date.AppDate
import com.example.zetutorial.utils.toMd5
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticateInterceptorImpl @Inject constructor(
    private val appDate: AppDate,
    @PrivateApiQualifier private val privateKey: String = BuildConfig.API_PRIVATE_KEY,
    @PublicApiQualifier private val publicKey: String = BuildConfig.API_PUBLIC_KEY
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newUrl = request.url.newBuilder()
            .addQueryParameter(API_KEY, publicKey)
            .addQueryParameter(TS_KEY, getTimestamp())
            .addQueryParameter(HASH_KEY, generateKey())
            .build()

        val requestBuilder = request.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(requestBuilder)
    }

    private fun generateKey(): String {
        val timestamp = getTimestamp()
        val token = timestamp.plus(privateKey).plus(publicKey)
        return token.toMd5()
    }

    private fun getTimestamp(): String {
        return appDate.getDate().toString()
    }

    private companion object {
        const val API_KEY = "apikey"
        const val HASH_KEY = "hash"
        const val TS_KEY = "ts"
    }
}