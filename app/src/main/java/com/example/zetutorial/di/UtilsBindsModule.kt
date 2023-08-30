package com.example.zetutorial.di

import com.example.zetutorial.utils.date.AppDate
import com.example.zetutorial.utils.date.AppDateImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsBindsModule {

    @Binds
    abstract fun bindsAppDate(appDateImpl: AppDateImpl): AppDate
}