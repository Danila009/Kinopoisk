package com.example.kinopoisk.api.model.user.history

data class History(
    val date: String,
    val id: Int? = null,
    val kinopoiskId: Int,
    val nameRu: String,
    val posterUrlPreview: String,
    val ratingKinopoisk: Float
)