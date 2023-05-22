package com.example.rick_and_morty_android_networking_example_app.domain.models

import com.example.rick_and_morty_android_networking_example_app.data.api.dto.CharacterDTO
import com.google.gson.annotations.SerializedName


data class Info(
    val count: Int?,
    val next: String?,
    val pages: Int?,
    val prev: String?
)

data class CharacterResponse(
    val info: Info,
    val results: List<CharacterDTO>
)