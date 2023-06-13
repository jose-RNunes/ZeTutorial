package com.example.zetutorial.data.repository

import com.example.zetutorial.data.remote.api.MarvelApi
import com.example.zetutorial.data.remote.mapper.CharacterResponseToModelMapper
import com.example.zetutorial.domain.model.CharacterModel
import com.example.zetutorial.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val characterResponseToModelMapper: CharacterResponseToModelMapper,
    private val marvelApi: MarvelApi
): CharacterRepository {

    override suspend fun fetchCharacters(): List<CharacterModel> {
        return marvelApi.fetchCharacters().results.map { characterResponse ->
            characterResponseToModelMapper.converter(characterResponse)
        }
    }
}