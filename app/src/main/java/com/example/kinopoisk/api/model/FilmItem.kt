package com.example.kinopoisk.api.model

import kotlinx.serialization.Serializable

@Serializable
data class FilmItem(
    val kinopoiskId:Int = 0,
    val imdbId:String = "",
    val nameRu:String? = null,
    val nameEn:String? = null,
    val nameOriginal:String? = null,
    val ratingKinopoisk:Float? = null,
    val ratingImdb:Float? = null,
    val year:Int? = null,
    val type:String? = null,
    val posterUrl:String? = null,
    val posterUrlPreview:String? = null
)