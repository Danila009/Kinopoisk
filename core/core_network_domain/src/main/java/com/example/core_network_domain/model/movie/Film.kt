package com.example.core_network_domain.model.movie

data class Film(
    val total:Int = 0,
    val totalPages:Int = 0,
    val items:List<FilmItem> = listOf()
)