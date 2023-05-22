package com.example.rick_and_morty_android_networking_example_app.di

import com.example.rick_and_morty_android_networking_example_app.domain.repository.RickAndMortyRepository.CharacterRepository
import com.example.rick_and_morty_android_networking_example_app.domain.use_cases.RickAndMortyUseCases
import com.example.rick_and_morty_android_networking_example_app.domain.use_cases.implementation.CharacterUseCasesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideCharacterUseCases(repository: CharacterRepository): RickAndMortyUseCases.CharacterUseCases
            = CharacterUseCasesImpl(repository)
}