package com.example.kinopoisk.api.model.topFilm

import com.example.kinopoisk.api.model.filmInfo.Countrie
import com.example.kinopoisk.api.model.filmInfo.Genre

data class TopItem(
    val filmId:Int,
    val nameRu:String,
    val nameEn:String,
    val year:String,
    val filmLength:String,
    val countries:List<Countrie>,
    val genres:List<Genre>,
    val rating:String,
    val ratingVoteCount:String,
    val posterUrl:String,
    val posterUrlPreview:String
)
