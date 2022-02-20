package com.example.kinopoisk.api.model.cinema

data class Premiere(
    val id: Int,
    val kinopoiskId: Int,
    val nameRu: String,
    val posterUrlPreview: String,
    val ratingKinopoisk: String
)