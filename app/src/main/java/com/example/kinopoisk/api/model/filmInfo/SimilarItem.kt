package com.example.kinopoisk.api.model.filmInfo

data class SimilarItem(
    val filmId:Int,
    val nameRu:String,
    val nameEn:String,
    val nameOriginal:String,
    val posterUrl:String? = null,
    val posterUrlPreview:String? = null,
    val relationType:String
)