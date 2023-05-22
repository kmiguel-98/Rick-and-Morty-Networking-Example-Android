package com.example.rick_and_morty_android_networking_example_app.data.api.implementation

import com.example.rick_and_morty_android_networking_example_app.data.api.RickAndMortyAPI
import com.example.rick_and_morty_android_networking_example_app.data.retrofit_service.RickAndMortyAPIService
import com.example.rick_and_morty_android_networking_example_app.data.api.dto.toCharacterObject
import com.example.rick_and_morty_android_networking_example_app.domain.models.Character
import retrofit2.Retrofit
import javax.inject.Inject

class RickAndMortyCharacterAPIImpl @Inject constructor(
    retrofit: Retrofit
) : RickAndMortyAPI.RickAndMortyCharacterAPI {

    private val api = retrofit
        .create(RickAndMortyAPIService.CharacterService::class.java)

    override suspend fun fetchCharacters(page: String): List<Character> {

        val characterResponse = api.fetchCharacters(page)
        return characterResponse.results.map { it.toCharacterObject() }
    }

    override suspend fun fetchSingleCharacter(id: String): Character {

        val response = api.fetchSingleCharacter(id)
        return response.toCharacterObject()
    }
}