package com.example.core_network_domain.model.movie.staff

import kotlinx.serialization.Serializable

@Serializable
data class FilmStaff(
    val description: String?,
    val filmId: Int = 0,
    val general: Boolean?,
    val nameEn: String?,
    val nameRu: String?,
    val professionKey: String?,
    val rating: String? = null
)