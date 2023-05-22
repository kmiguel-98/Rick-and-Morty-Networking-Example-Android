package com.example.rick_and_morty_android_networking_example_app.domain.repository

import com.example.rick_and_morty_android_networking_example_app.common.Resource
import com.example.rick_and_morty_android_networking_example_app.domain.models.Character
import kotlinx.coroutines.flow.Flow

class RickAndMortyRepository {

    interface CharacterRepository {

        suspend fun getCharacters(page: String): Resource<List<Character>>

        suspend fun getSingleCharacter(id: String): Resource<Character>
    }
}