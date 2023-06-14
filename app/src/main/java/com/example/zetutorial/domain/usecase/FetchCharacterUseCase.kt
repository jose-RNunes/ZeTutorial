package com.example.zetutorial.domain.usecase

import com.example.zetutorial.domain.model.CharacterModel
import com.example.zetutorial.domain.repository.CharacterRepository

class FetchCharacterUseCase(private val characterRepository: CharacterRepository) {

    suspend operator fun invoke(): List<CharacterModel> {
        return characterRepository.fetchCharacters()
    }
}
