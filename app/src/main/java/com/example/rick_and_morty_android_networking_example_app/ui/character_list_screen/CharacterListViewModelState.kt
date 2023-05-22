package com.example.rick_and_morty_android_networking_example_app.ui.character_list_screen

import com.example.rick_and_morty_android_networking_example_app.domain.models.Character

data class CharacterListViewModelState(
    var characterList: MutableList<Character> = mutableListOf(),
    var error: String = "",
    var isLoading: Boolean = false
)