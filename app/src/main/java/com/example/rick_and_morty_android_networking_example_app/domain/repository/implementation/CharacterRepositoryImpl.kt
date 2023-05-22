package com.example.rick_and_morty_android_networking_example_app.domain.repository.implementation

import com.example.rick_and_morty_android_networking_example_app.common.Resource
import com.example.rick_and_morty_android_networking_example_app.common.Resource.*
import com.example.rick_and_morty_android_networking_example_app.data.api.RickAndMortyAPI
import com.example.rick_and_morty_android_networking_example_app.domain.models.Character
import com.example.rick_and_morty_android_networking_example_app.domain.repository.RickAndMortyRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
   private val apiManager: RickAndMortyAPI.RickAndMortyCharacterAPI
): RickAndMortyRepository.CharacterRepository {

    override suspend fun getCharacters(page: String): Resource<List<Character>> {
        val characterList = apiManager.fetchCharacters(page)
        return Success(characterList)
    }

    override suspend fun getSingleCharacter(id: String): Resource<Character> {
        val character = apiManager.fetchSingleCharacter(id)
        return Success(character)
    }
}