package com.example.zetutorial.data.remote.mapper

import com.example.zetutorial.data.remote.response.CharacterResponse
import com.example.zetutorial.domain.model.CharacterModel
import com.example.zetutorial.utils.Mapper

class CharacterResponseToModelMapper : Mapper<CharacterResponse, CharacterModel> {

    override fun converter(from: CharacterResponse): CharacterModel {
        return CharacterModel(
            id = from.id,
            name = from.name,
            description = from.description,
            thumbnailUrl = from.thumbnail.path.plus(DOT).plus(from.thumbnail.extension)
        )
    }

    private companion object {
        const val DOT = "."
    }
}
