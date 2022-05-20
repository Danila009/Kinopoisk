package com.example.core_network_domain.model.movie.staff

import com.example.core_network_domain.serialization.DateSerialization
import kotlinx.serialization.Serializable

@Serializable
data class StaffInfo(
    val age: Int? = null,
    @Serializable(with = DateSerialization::class)
    val birthday: String? = null,
    val birthplace: String? = null,
    @Serializable(with = DateSerialization::class)
    val death: String? = null,
    val deathplace: String? = null,
    val facts: List<String>? = listOf(),
    val films: List<FilmStaff>? = listOf(),
    val growth: String? = null,
    val hasAwards: Int? = null,
    val nameEn: String? = null,
    val nameRu: String? = null,
    val personId: Int? = null,
    val posterUrl: String? = null,
    val profession: String? = null,
    val sex: String? = null,
    val spouses: List<SpouseStaff>? = null,
    val webUrl: String? = null
)