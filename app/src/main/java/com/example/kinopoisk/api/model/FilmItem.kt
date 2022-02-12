package com.example.kinopoisk.api.model

data class FilmItem(
    val kinopoiskId:Int,
    val imdbId:String,
    val nameRu:String?,
    val nameEn:String?,
    val nameOriginal:String?,
    val ratingKinopoisk:Float?,
    val ratingImdb:Float?,
    val year:Int?,
    val type:String?,
    val posterUrl:String?,
    val posterUrlPreview:String?
)