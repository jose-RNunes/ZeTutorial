package com.example.zetutorial.di

import com.example.zetutorial.BuildConfig
import com.example.zetutorial.data.remote.api.MarvelApi
import com.example.zetutorial.di.qualifiers.AuthenticateInterceptorQualifier
import com.example.zetutorial.di.qualifiers.LoggingInterceptorQualifier
import com.example.zetutorial.di.qualifiers.PrivateApiQualifier
import com.example.zetutorial.di.qualifiers.PublicApiQualifier
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataProvidesModule {

    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @LoggingInterceptorQualifier
    @Provides
    @Singleton
    fun providesLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @PublicApiQualifier
    @Provides
    @Singleton
    fun providesPublicApiKey(): String {
        return BuildConfig.API_PUBLIC_KEY
    }

    @PrivateApiQualifier
    @Provides
    @Singleton
    fun providesPrivateApiKey(): String {
        return BuildConfig.API_PRIVATE_KEY
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @AuthenticateInterceptorQualifier authenticateInterceptor: Interceptor,
        @LoggingInterceptorQualifier loggingInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authenticateInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesMarvelApi(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }
}