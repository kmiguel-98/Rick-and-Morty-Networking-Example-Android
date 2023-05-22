package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen

import com.example.rick_and_morty_android_networking_example_app.domain.models.Character

data class CharacterListViewModelState(
    var characterList: MutableList<Character> = mutableListOf(),
    var error: String = "",
    var isLoading: Boolean = false
)

fun CharacterListViewModelState.update(
    characterList: MutableList<Character>? = null,
    error: String? = null,
    isLoading: Boolean? = null
) {

    characterList?.let { this.characterList = characterList }
    error?.let { this.error = error }
    isLoading?.let { this.isLoading = isLoading }
}