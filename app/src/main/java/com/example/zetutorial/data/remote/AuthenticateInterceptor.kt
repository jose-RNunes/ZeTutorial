package com.example.zetutorial.data.remote

import com.example.zetutorial.di.qualifiers.PrivateKeyQualifier
import com.example.zetutorial.di.qualifiers.PublicKeyQualifier
import com.example.zetutorial.utils.AppDate
import com.example.zetutorial.utils.toMd5
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthenticateInterceptorImpl @Inject constructor(
    private val appDate: AppDate,
    @PublicKeyQualifier private val publicKey: String,
    @PrivateKeyQualifier private val privateKey: String
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
       val request = chain.request()

        val newUrl = request.url.newBuilder()
            .addQueryParameter(TS_KEY, getTimestamp())
            .addQueryParameter(PUBLIC_KEY, publicKey)
            .addQueryParameter(HASH_KEY, getAuthKey())
            .build()

        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }

    private fun getAuthKey(): String {
        val key = getTimestamp().plus(privateKey).plus(publicKey)
        return key.toMd5()
    }

    private fun getTimestamp(): String {
        return appDate.getDate().time.toString()
    }

    private companion object {
        const val TS_KEY = "ts"
        const val PUBLIC_KEY = "apikey"
        const val HASH_KEY = "hash"
    }
}