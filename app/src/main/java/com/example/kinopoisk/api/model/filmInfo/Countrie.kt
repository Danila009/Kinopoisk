package com.example.kinopoisk.api.model.filmInfo

import kotlinx.serialization.Serializable

@Serializable
data class Countrie(
    val id:Int? = null,
    val country:String
)