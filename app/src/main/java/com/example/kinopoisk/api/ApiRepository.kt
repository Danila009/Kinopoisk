package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.Film
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.filmInfo.Budget
import com.example.kinopoisk.api.model.filmInfo.Fact
import com.example.kinopoisk.api.model.filmInfo.Similar
import com.example.kinopoisk.api.model.staff.Staff
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

    suspend fun getFilmInfo(id:Int):Response<FilmInfo> = apiKinopoisk.getFilmInfo(id)

    suspend fun getBudget(id: Int):Response<Budget> = apiKinopoisk.getBudget(id)

    suspend fun getFact(id: Int):Response<Fact> = apiKinopoisk.getFact(id)

    suspend fun getStaff(id: Int):Response<List<Staff>> = apiKinopoisk.getStaff(id)

    suspend fun getSimilar(id: Int):Response<Similar> = apiKinopoisk.getSimilar(id)
}