package com.example.zetutorial.presentation.ui.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.zetutorial.presentation.uimodel.CharacterUiModel

class CharacterActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel = ViewModelProvider(this)[CharacterViewModel::class.java]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindObservers()
        viewModel.handleAction(CharacterAction.GetActors)
    }

    private fun bindObservers() {
        viewModel.state.observe(this) { state ->
            // binding.showLoading = state.showLoading
            // binding.showError = state.showError

            if(state.showData()) {
                showActors(state.characters)
            }
        }
    }

    private fun showActors(actors: List<CharacterUiModel>) {
        // adapter.submitList(actors
    }
}
