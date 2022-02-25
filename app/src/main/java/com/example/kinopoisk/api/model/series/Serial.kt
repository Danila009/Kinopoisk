package com.example.kinopoisk.api.model.series

data class Serial(
    val id: Int? = null,
    val idKinopoisk: Int,
    val season: Int,
    val series: Int,
    val viewed: Boolean
)