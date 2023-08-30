package com.example.zetutorial.di

import android.content.Context
import com.example.zetutorial.data.remote.api.AuthenticateInterceptorImpl
import com.example.zetutorial.data.remote.mapper.CharacterResponseToModelMapper
import com.example.zetutorial.data.remote.mapper.CharacterResponseToModelMapperImpl
import com.example.zetutorial.data.repository.CharacterRepositoryImpl
import com.example.zetutorial.di.qualifiers.AuthenticateInterceptorQualifier
import com.example.zetutorial.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindsModule {

    @Binds
    abstract fun providesContext(@ApplicationContext context: Context): Context

    @AuthenticateInterceptorQualifier
    @Binds
    abstract fun bindsAuthenticateInterceptor(
        authenticateInterceptorImpl: AuthenticateInterceptorImpl
    ): Interceptor

    @Binds
    abstract fun bindsCharacterResponseToModelMapper(
        characterResponseToModelMapperImpl: CharacterResponseToModelMapperImpl
    ): CharacterResponseToModelMapper

    @Binds
    abstract fun bindsCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository
}