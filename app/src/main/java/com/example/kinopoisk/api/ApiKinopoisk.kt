package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.Film
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.filmInfo.Budget
import com.example.kinopoisk.api.model.filmInfo.Fact
import com.example.kinopoisk.api.model.filmInfo.Similar
import com.example.kinopoisk.api.model.staff.Staff
import com.example.kinopoisk.utils.Constants.BUDGET_FILM_ID_URL
import com.example.kinopoisk.utils.Constants.FACT_FILM_ID_URL
import com.example.kinopoisk.utils.Constants.FILM_INFO_ID_URL
import com.example.kinopoisk.utils.Constants.FILM_URL
import com.example.kinopoisk.utils.Constants.SIMILAR_ID_URL
import com.example.kinopoisk.utils.Constants.STAFf_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET(FILM_INFO_ID_URL)
    suspend fun getFilmInfo(
        @Path("id") id:Int
    ):Response<FilmInfo>

    @GET(BUDGET_FILM_ID_URL)
    suspend fun getBudget(
        @Path("id") id: Int
    ):Response<Budget>

    @GET(FACT_FILM_ID_URL)
    suspend fun getFact(
        @Path("id") id: Int
    ):Response<Fact>

    @GET(STAFf_URL)
    suspend fun getStaff(
        @Query("filmId") id: Int
    ):Response<List<Staff>>

    @GET(SIMILAR_ID_URL)
    suspend fun getSimilar(
        @Path("id") id: Int
    ):Response<Similar>
}