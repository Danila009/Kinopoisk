package com.example.core_network_domain.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class MovieItem(
    val id: Int?,
    val imdbId: String?,
    val kinopoiskId: Int?,
    val nameEn: String?,
    val nameOriginal: String?,
    val nameRu: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val ratingImdb: Int?,
    val ratingKinopoisk: Int?,
    val type: String?,
    val year: Int?
)