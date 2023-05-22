package com.example.rick_and_morty_android_networking_example_app.data.api.dto

import com.example.rick_and_morty_android_networking_example_app.domain.models.Location
import com.google.gson.annotations.SerializedName
import java.lang.Exception
import java.net.URL

data class LocationDTO(
    val name: String?,
    val url: String?
)

fun LocationDTO.toLocationObject() = Location(
    name = this.name ?: "",
    url = runCatching { URL(this.url!!) }.getOrNull()
)