package com.example.kinopoisk.api.model.staff

data class FilmStaff(
    val description: String?,
    val filmId: Int = 0,
    val general: Boolean?,
    val nameEn: String?,
    val nameRu: String?,
    val professionKey: String?,
    val rating: String? = null
)