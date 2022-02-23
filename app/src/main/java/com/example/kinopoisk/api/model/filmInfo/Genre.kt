package com.example.kinopoisk.api.model.filmInfo

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id:Int? = null,
    val genre:String
)