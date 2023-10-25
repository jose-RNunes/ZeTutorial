package com.example.zetutorial.di.qualifiers

import com.example.zetutorial.domain.model.CharacterModel
import com.example.zetutorial.presentation.mapper.CharacterModelToUiModelMapper
import com.example.zetutorial.presentation.uimodel.CharacterUiModel
import com.example.zetutorial.utils.Mapper
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
    abstract fun bindsResourceProvider(
        resourceProviderImpl: ResourceProviderImpl
    ): ResourceProvider

    @Binds
    abstract fun bindsCharacterModelToUiModel(
        characterModelToUiModel: CharacterModelToUiModelMapper
    ): Mapper<CharacterModel, CharacterUiModel>
}