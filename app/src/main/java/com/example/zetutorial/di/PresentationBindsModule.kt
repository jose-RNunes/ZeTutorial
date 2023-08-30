package com.example.zetutorial.di

import com.example.zetutorial.presentation.mapper.CharacterModelToUiModelMapper
import com.example.zetutorial.presentation.mapper.CharacterModelToUiModelMapperImpl
import com.example.zetutorial.utils.ResourceProvider
import com.example.zetutorial.utils.ResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PresentationBindsModule {

    @Binds
    abstract fun bindsResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider

    @Binds
    abstract fun bindsCharacterModelToUiModel(
        characterModelToUiModelImpl: CharacterModelToUiModelMapperImpl
    ): CharacterModelToUiModelMapper
}