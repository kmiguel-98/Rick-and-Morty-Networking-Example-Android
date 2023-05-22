package com.example.rick_and_morty_android_networking_example_app.data.api

import com.example.rick_and_morty_android_networking_example_app.domain.models.Character

class RickAndMortyAPI {

    interface RickAndMortyCharacterAPI {

        suspend fun fetchCharacters(page: String): List<Character>

        suspend fun fetchSingleCharacter(id: String): Character
    }
}