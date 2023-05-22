package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty_android_networking_example_app.common.Constants
import com.example.rick_and_morty_android_networking_example_app.common.Resource
import com.example.rick_and_morty_android_networking_example_app.domain.models.Character
import com.example.rick_and_morty_android_networking_example_app.domain.use_cases.RickAndMortyUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterUseCases: RickAndMortyUseCases.CharacterUseCases
) : ViewModel() {

    val TAG = "CharacterListViewModel"
    var state = MutableStateFlow(CharacterListViewModelState())
    private set

    private var currentPage = 1
    // Public_Methods
    fun recyclerViewDidLoad() {
        viewModelScope.launch {
            val characterList = getCharacters(currentPage)
            state.value = CharacterListViewModelState(characterList.toMutableList())
        }

    }

    fun recyclerDidScrollUntilBottom() {
        currentPage++
        if (currentPage <= Constants.LAST_PAGE_NUMBER) {

            viewModelScope.launch {
                val currentList = state.value.characterList
                val characterList = getCharacters(currentPage)
                state.value = CharacterListViewModelState((currentList+ characterList).toMutableList())
                Log.d(TAG, "recyclerDidScrollUntilBottom: ${state.value.characterList.count()}")
            }
        }
    }

    fun recyclerDidMadeRefreshGesture() {
        currentPage = 0

        viewModelScope.launch {
            val currentList = state.value.characterList
            val characterList = getCharacters(currentPage)
            state.value = CharacterListViewModelState((currentList+ characterList).toMutableList())
        }

    }

    // Private_Methods
    private suspend fun getCharacters(page: Int): List<Character> = suspendCoroutine { continuation ->
        var characterList: List<Character> = emptyList()

        characterUseCases.getCharacters(page).onEach { resource ->
            when(resource) {
                is Resource.Success -> {
                    characterList = resource.data ?: emptyList()
                }

                is Resource.Failure ->
                    state.value = CharacterListViewModelState(error = resource.message.toString())

                is Resource.Loading ->
                    state.value = CharacterListViewModelState(isLoading = true)
            }

            if (resource is Resource.Success || resource is Resource.Failure) {
                continuation.resume(characterList)
            }
        }.launchIn(viewModelScope)
    }

}