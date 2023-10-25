package com.example.zetutorial.presentation.ui.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zetutorial.domain.usecase.FetchCharacterUseCase
import com.example.zetutorial.presentation.mapper.CharacterModelToUiModelMapper
import com.example.zetutorial.presentation.uimodel.CharacterUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val fetchCharacters: FetchCharacterUseCase,
    private val characterModelToUiModelMapper: CharacterModelToUiModelMapper
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
                    characterModelToUiModelMapper.converter(characterModel)
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
