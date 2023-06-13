package com.example.zetutorial.presentation.ui.character

import com.example.zetutorial.presentation.uimodel.CharacterUiModel

data class CharacterState(
    val showLoading: Boolean = false,
    val showError: Boolean = false,
    val characters: List<CharacterUiModel> = emptyList(),
    val characterSelected: CharacterUiModel? = null
) {

    fun showData() = characters.isNotEmpty()

    fun showError() = showLoading.not() && showError

    fun setCharacters(characters: List<CharacterUiModel>) = copy(
        showLoading = false,
        showError = false,
        characters = characters
    )

    fun setSelectCharacter(characterSelected: CharacterUiModel) = copy(
        characterSelected = characterSelected
    )

    fun setShowError() = copy(showLoading = false, showError = true)

    fun setShowLoading() = copy(showLoading = true)
}