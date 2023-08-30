package com.example.zetutorial.domain.usecase

import com.example.zetutorial.domain.model.CharacterModel
import com.example.zetutorial.domain.repository.CharacterRepository
import javax.inject.Inject

class FetchCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    suspend operator fun invoke(): List<CharacterModel> {
        return characterRepository.fetchCharacters()
    }
}
