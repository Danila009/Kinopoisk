package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.Film
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiKinopoisk: ApiKinopoisk
) {
    suspend fun getFilm(
        order:String,
        type:String,
        ratingFrom:Int,
        ratingTo:Int,
        yearFrom:Int,
        yearTo:Int,
        keyword:String,
        page:Int = 1
    ):Response<Film> = apiKinopoisk.getFilm(
        order = order,
        type = type,
        ratingFrom = ratingFrom,
        ratingTo =ratingTo,
        yearFrom = yearFrom,
        yearTo = yearTo,
        keyword = keyword,
        page = page
    )
}