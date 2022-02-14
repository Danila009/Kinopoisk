package com.example.kinopoisk.api.model.seasons

data class Season(
    val total:Int? = null,
    val item:List<SeasonItem> = listOf()
)