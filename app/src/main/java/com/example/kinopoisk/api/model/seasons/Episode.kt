package com.example.kinopoisk.api.model.seasons

data class Episode(
    val seasonNumber:Int = 0,
    val episodeNumber:Int = 0,
    val nameRu:String? = "",
    val nameEn:String? = "",
    val synopsis:String? = "",
    val releaseDate:String? = ""
)