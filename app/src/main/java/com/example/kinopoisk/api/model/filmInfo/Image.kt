package com.example.kinopoisk.api.model.filmInfo

data class Image(
    val total:Int? = null,
    val totalPages:Int = 1,
    val items:List<ImageItem> = listOf()
)
