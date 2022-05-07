package com.example.core_network_domain.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val total:Int = 0,
    val items:List<MovieItem> = emptyList()
)