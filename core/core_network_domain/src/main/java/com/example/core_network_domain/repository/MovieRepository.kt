package com.example.core_network_domain.repository

import com.example.core_network_domain.model.movie.Film
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.Release

interface MovieRepository {

    suspend fun getPremiere(year:Int, month: String):Premiere

    suspend fun getRelease(year:Int, month: String, page:Int): Release

    suspend fun getFilm(
        genres:List<Int>,
        countries:List<Int>,
        order:String,
        type:String,
        ratingFrom:Int,
        ratingTo:Int,
        yearFrom:Int,
        yearTo:Int,
        keyword:String,
        page:Int
    ):Film
}