package com.example.rick_and_morty_android_networking_example_app.ui.character_detail_screen

import com.example.rick_and_morty_android_networking_example_app.domain.models.Character

data class CharacterDetailViewModelState(
    var character: Character? = null,
    var isLoading: Boolean = false,
    var error: String? = null
)
