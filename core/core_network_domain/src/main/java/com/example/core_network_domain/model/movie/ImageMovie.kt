package com.example.core_network_domain.model.movie

data class ImageMovie(
    val total:Int? = null,
    val totalPages:Int = 1,
    val items:List<ImageMovieItem> = listOf()
)
