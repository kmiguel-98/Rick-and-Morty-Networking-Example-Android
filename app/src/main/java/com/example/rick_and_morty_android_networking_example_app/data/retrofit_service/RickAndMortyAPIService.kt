package com.example.rick_and_morty_android_networking_example_app.data.retrofit_service

import com.example.rick_and_morty_android_networking_example_app.data.api.dto.CharacterDTO
import com.example.rick_and_morty_android_networking_example_app.domain.models.CharacterResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class RickAndMortyAPIService {

    interface CharacterService {

        @GET("character/")
        suspend fun fetchCharacters(@Query("page") paramValue: String): CharacterResponse

        @GET("character/{id}")
        suspend fun fetchSingleCharacter(@Path("id") characterId: String): CharacterDTO
    }
}