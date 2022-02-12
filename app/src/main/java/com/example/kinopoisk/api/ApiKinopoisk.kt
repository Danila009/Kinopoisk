package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.Film
import com.example.kinopoisk.utils.Constants.FILM_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiKinopoisk {

    @GET(FILM_URL)
    suspend fun getFilm(
        @Query("order") order:String, //Сортировка
        @Query("type") type:String, //тип фильма
        @Query("ratingFrom") ratingFrom:Int, //минимальный рейтинг
        @Query("ratingTo") ratingTo:Int, // максимальный рейтинг
        @Query("yearFrom") yearFrom:Int, // минимальный год
        @Query("yearTo") yearTo:Int, //максимальный год
        @Query("keyword") keyword:String, // Search
        @Query("page") page:Int //номер страницы
    ):Response<Film>
}