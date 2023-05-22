package com.example.rick_and_morty_android_networking_example_app.domain.use_cases

import com.example.rick_and_morty_android_networking_example_app.common.Resource
import com.example.rick_and_morty_android_networking_example_app.domain.models.Character
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class RickAndMortyUseCases @Inject constructor() {

    interface CharacterUseCases {

        fun getCharacters(pageNumber: Int): Flow<Resource<List<Character>>>

        fun getSingleCharacter(id: String): Flow<Resource<Character>>
    }
}