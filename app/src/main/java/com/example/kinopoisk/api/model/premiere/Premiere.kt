package com.example.kinopoisk.api.model.premiere

data class Premiere(
    val total:Int? = null,
    val items:List<PremiereItem> = listOf()
)