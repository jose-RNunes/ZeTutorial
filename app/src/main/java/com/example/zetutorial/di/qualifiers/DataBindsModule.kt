package com.example.zetutorial.di.qualifiers

import com.example.zetutorial.data.remote.AuthenticateInterceptorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindsModule {

    @AuthenticateInterceptorQualifier
    @Singleton
    @Binds
    abstract fun bindsAuthenticateInterceptor(
        authenticateInterceptorImpl: AuthenticateInterceptorImpl
    ): Interceptor
}