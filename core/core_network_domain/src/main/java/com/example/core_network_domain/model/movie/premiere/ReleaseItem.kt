package com.example.core_network_domain.model.movie.premiere

import com.example.core_network_domain.model.movie.Countrie
import com.example.core_network_domain.model.movie.Genre

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
