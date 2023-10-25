package com.example.zetutorial.di.qualifiers

import android.content.Context
import com.example.zetutorial.data.remote.AuthenticateInterceptorImpl
import com.example.zetutorial.data.remote.ErrorInterceptorImpl
import com.example.zetutorial.data.remote.mapper.CharacterResponseToModelMapper
import com.example.zetutorial.data.remote.response.CharacterResponse
import com.example.zetutorial.data.repository.CharacterRepositoryImpl
import com.example.zetutorial.domain.model.CharacterModel
import com.example.zetutorial.domain.repository.CharacterRepository
import com.example.zetutorial.utils.AppDate
import com.example.zetutorial.utils.AppDateImpl
import com.example.zetutorial.utils.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindsModule {

    @Singleton
    @Binds
    abstract fun providesContext(@ApplicationContext context: Context): Context

    @AuthenticateInterceptorQualifier
    @Singleton
    @Binds
    abstract fun bindsAuthenticateInterceptor(
        authenticateInterceptorImpl: AuthenticateInterceptorImpl
    ): Interceptor

    @ErrorInterceptorQualifier
    @Singleton
    @Binds
    abstract fun bindsErrorInterceptor(
        errorInterceptorImpl: ErrorInterceptorImpl
    ): Interceptor

    @Binds
    abstract fun bindsDate(appDateImpl: AppDateImpl): AppDate

    @Binds
    abstract fun bindsCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    abstract fun bindsCharacterResponseToModelMapper(
        characterResponseToModelMapper: CharacterResponseToModelMapper
    ): Mapper<CharacterResponse, CharacterModel>
}