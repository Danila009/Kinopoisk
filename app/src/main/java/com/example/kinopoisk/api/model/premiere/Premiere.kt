package com.example.kinopoisk.api.model.premiere

data class Premiere(
    val total:Int = 0,
    val items:List<PremiereItem> = listOf()
)