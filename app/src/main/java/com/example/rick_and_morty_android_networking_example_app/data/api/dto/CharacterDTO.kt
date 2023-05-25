package com.example.rick_and_morty_android_networking_example_app.data.api.dto

import com.example.rick_and_morty_android_networking_example_app.domain.models.Character
import com.example.rick_and_morty_android_networking_example_app.domain.models.Gender
import com.example.rick_and_morty_android_networking_example_app.domain.models.Location
import com.example.rick_and_morty_android_networking_example_app.domain.models.Status
import com.google.gson.annotations.SerializedName
import java.lang.Exception
import java.net.URL

data class CharacterDTO(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val location: LocationDTO?,
    val name: String?,
    val origin: LocationDTO?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
)

fun CharacterDTO.toCharacterObject(): Character = Character(
    id = this.id ?: (3000..7000).random(),
    name = this.name ?: "",
    status = Status.fromValue(this.status ?: ""),
    species = this.species ?: "",
    type = this.type ?: "",
    gender = Gender.fromValue(this.gender ?: ""),
    origin = this.origin?.toLocationObject() ?: Location("", null),
    location = this.location?.toLocationObject() ?: Location("", null),
    image = kotlin.runCatching { URL(this.image!!) }.getOrNull(),
    episode = this.episode ?: emptyList(),
    url = runCatching { URL(this.url!!) }.getOrNull(),
    created = this.created ?: ""
)

