package com.example.kinopoisk.api.model.filmInfo

data class Similar(
    val total:Int? = null,
    val items:List<SimilarItem> = listOf()
)