package com.example.kinopoisk.api.model.filmInfo

data class Fact(
    val total:Int? = null,
    val items:List<FactItem> = listOf()
)