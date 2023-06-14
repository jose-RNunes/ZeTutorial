package com.example.zetutorial.presentation.mapper

import com.example.zetutorial.R
import com.example.zetutorial.domain.model.CharacterModel
import com.example.zetutorial.presentation.uimodel.CharacterUiModel
import com.example.zetutorial.utils.Mapper
import com.example.zetutorial.utils.ResourceProvider

class CharacterModelToUiModel(
    private val resourceProvider: ResourceProvider
) : Mapper<CharacterModel, CharacterUiModel> {

    override fun converter(from: CharacterModel): CharacterUiModel {
        return CharacterUiModel(
            id = from.id,
            name = from.name,
            thumbnailUrl = from.thumbnailUrl,
            description = from.description,
            descriptionTitle = resourceProvider.getString(R.string.description),
            hasDescription = from.description.isNotEmpty(),
            placeholder = resourceProvider.getDrawable(R.drawable.ic_launcher_background)
        )
    }
}
