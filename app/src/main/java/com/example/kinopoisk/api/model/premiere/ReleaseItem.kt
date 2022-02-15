package com.example.kinopoisk.api.model.premiere

import com.example.kinopoisk.api.model.filmInfo.Countrie
import com.example.kinopoisk.api.model.filmInfo.Genre

data class ReleaseItem(
    val filmId:Int,
    val nameRu:String,
    val nameEn:String,
    val year:Int,
    val posterUrl:String,
    val posterUrlPreview:String,
    val countries:List<Countrie>,
    val genres:List<Genre>,
    val rating:Float,
    val ratingVoteCount:Int,
    val expectationsRating:Float,
    val expectationsRatingVoteCount:Int,
    val duration:Int,
    val releaseDate:String
)
