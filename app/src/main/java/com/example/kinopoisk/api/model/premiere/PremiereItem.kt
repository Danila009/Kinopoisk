package com.example.kinopoisk.api.model.premiere

import com.example.kinopoisk.api.model.filmInfo.Countrie
import com.example.kinopoisk.api.model.filmInfo.Genre

data class PremiereItem(
    val kinopoiskId:Int = 0,
    val nameRu:String = "",
    val nameEn:String = "",
    val year:Int = 0,
    val posterUrl:String = "",
    val posterUrlPreview:String = "",
    val countries:List<Countrie> = listOf(),
    val genres:List<Genre> = listOf(),
    val duration:Int = 0,
    val premiereRu:String = ""
)