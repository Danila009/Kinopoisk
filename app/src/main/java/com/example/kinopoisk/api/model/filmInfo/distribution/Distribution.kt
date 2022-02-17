package com.example.kinopoisk.api.model.filmInfo.distribution

data class Distribution(
    val total:Int? = null,
    val items:List<DistributionItem> = listOf()
)
