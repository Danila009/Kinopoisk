package com.example.core_network_domain.model.serial

import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val seasonNumber:Int = 0,
    val episodeNumber:Int = 0,
    val nameRu:String? = "",
    val nameEn:String? = "",
    val synopsis:String? = "",
    val releaseDate:String? = ""
)