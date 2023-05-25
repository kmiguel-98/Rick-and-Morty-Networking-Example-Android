package com.example.rick_and_morty_android_networking_example_app.ui.character_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty_android_networking_example_app.common.Resource
import com.example.rick_and_morty_android_networking_example_app.domain.use_cases.RickAndMortyUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterUseCases: RickAndMortyUseCases.CharacterUseCases
) : ViewModel() {

    var state = MutableStateFlow(CharacterDetailViewModelState())
    private set

    fun fetchCharacter(id: Int) {
        viewModelScope.launch {
            getSingleCharacter(id)
        }
    }

    private fun getSingleCharacter(id: Int) {

        characterUseCases.getSingleCharacter(id.toString()).onEach { response ->

            when(response) {
                is Resource.Success -> state.value = CharacterDetailViewModelState(character = response.data)
                is Resource.Failure -> state.value = CharacterDetailViewModelState(error = response.message)
                is Resource.Loading -> state.value = CharacterDetailViewModelState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}