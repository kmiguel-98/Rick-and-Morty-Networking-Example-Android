package com.example.rick_and_morty_android_networking_example_app.di

import com.example.rick_and_morty_android_networking_example_app.common.Constants
import com.example.rick_and_morty_android_networking_example_app.data.api.RickAndMortyAPI.*
import com.example.rick_and_morty_android_networking_example_app.data.api.implementation.RickAndMortyCharacterAPIImpl
import com.example.rick_and_morty_android_networking_example_app.domain.repository.RickAndMortyRepository.CharacterRepository
import com.example.rick_and_morty_android_networking_example_app.domain.repository.implementation.CharacterRepositoryImpl
import com.example.rick_and_morty_android_networking_example_app.domain.use_cases.RickAndMortyUseCases
import com.example.rick_and_morty_android_networking_example_app.domain.use_cases.implementation.CharacterUseCasesImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): RickAndMortyCharacterAPI
    = RickAndMortyCharacterAPIImpl(retrofit)

    @Provides
    @Singleton
    fun provideCharacterRepository(apiManager: RickAndMortyCharacterAPI): CharacterRepository
    = CharacterRepositoryImpl(apiManager)
}