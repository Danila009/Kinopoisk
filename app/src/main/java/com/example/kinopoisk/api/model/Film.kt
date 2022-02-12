package com.example.kinopoisk.api.model

data class Film(
    val total:Int,
    val totalPages:Int,
    val items:List<FilmItem>
)