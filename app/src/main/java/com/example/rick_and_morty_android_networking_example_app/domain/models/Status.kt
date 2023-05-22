package com.example.rick_and_morty_android_networking_example_app.domain.models

enum class Status(val value: String) {

    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown");

    companion object {
        fun allValues() = values().map { it.value }

        fun fromValue(value: String) = values().find { it.value == value } ?: UNKNOWN
    }
}