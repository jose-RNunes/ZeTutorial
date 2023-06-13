package com.example.zetutorial.domain.repository

import com.example.zetutorial.domain.model.CharacterModel

interface CharacterRepository {

    suspend fun fetchCharacters(): List<CharacterModel>
}