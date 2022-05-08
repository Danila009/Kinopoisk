package com.example.core_network_domain.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id:Int? = null,
    val genre:String
)