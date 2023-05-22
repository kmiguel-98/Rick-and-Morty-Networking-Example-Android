package com.example.rick_and_morty_android_networking_example_app.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Success<T>(data: T) : Resource<T>(data)
    class Failure<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data : T? = null) : Resource<T>(data)
}
