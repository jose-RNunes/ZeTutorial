package com.example.zetutorial.presentation.ui.character

import com.example.zetutorial.presentation.uimodel.CharacterUiModel

sealed interface CharacterAction {

    object GetActors: CharacterAction

    data class SelectCharacter(val characterUiModel: CharacterUiModel): CharacterAction
}