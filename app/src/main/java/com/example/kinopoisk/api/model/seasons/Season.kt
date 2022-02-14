package com.example.kinopoisk.api.model.seasons

data class Season(
    val total:Int? = null,
    val items:List<SeasonItem> = listOf()
)