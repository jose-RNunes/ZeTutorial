package com.example.zetutorial.presentation.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zetutorial.domain.usecase.FetchCharacterUseCase
import com.example.zetutorial.presentation.mapper.CharacterModelToUiModel
import com.example.zetutorial.presentation.uimodel.CharacterUiModel
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val fetchCharacters: FetchCharacterUseCase,
    private val characterModelToUiModel: CharacterModelToUiModel
) : ViewModel() {

    private val _state = MutableLiveData<CharacterState>()
    val state: LiveData<CharacterState> = _state

    fun init() {
        _state.value = getState().setShowLoading()
        viewModelScope.launch {
            runCatching {
                fetchCharacters()
            }.onSuccess { characters ->
                val charactersUi = characters.map { characterModel ->
                    characterModelToUiModel.converter(characterModel)
                }
                _state.value = getState().setCharacters(charactersUi)
            }.onFailure {
                getState().setShowError()
            }
        }
    }

    fun setSelectCharacter(characterUiModel: CharacterUiModel) {
        _state.value = getState().setSelectCharacter(characterUiModel)
    }

    private fun getState() = _state.value ?: CharacterState()
}
