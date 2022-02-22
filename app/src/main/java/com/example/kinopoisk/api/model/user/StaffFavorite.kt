package com.example.kinopoisk.api.model.user

data class StaffFavorite(
    val id: Int? = null,
    val nameEn: String,
    val nameRu: String,
    val posterUrl: String,
    val professionKey: String,
    val professionText: String,
    val staffId: Int
)