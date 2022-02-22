package com.example.kinopoisk.api.model.filmInfo.filter

import com.example.kinopoisk.api.model.filmInfo.Countrie
import com.example.kinopoisk.api.model.filmInfo.Genre

data class Filter(
    val genres:List<Genre> = listOf(),
    val countries:List<Countrie> = listOf()
)
