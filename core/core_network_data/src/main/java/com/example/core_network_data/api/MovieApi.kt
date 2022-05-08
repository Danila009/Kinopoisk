package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.FILM_INFO_ID_URL
import com.example.core_network_data.common.ConstantsUrl.FILM_URL
import com.example.core_network_data.common.ConstantsUrl.FILTER_URL
import com.example.core_network_data.common.ConstantsUrl.PREMIERE_URL
import com.example.core_network_data.common.ConstantsUrl.RELEASE_URL
import com.example.core_network_data.common.ConstantsUrl.SEASONS_ID_URL
import com.example.core_network_domain.model.movie.Film
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.movie.Filter
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.Release
import com.example.core_network_domain.model.serial.Season
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET(FILM_URL)
    suspend fun getFilm(
        @Query("genres") genres:List<Int>,
        @Query("countries") countries:List<Int>,
        @Query("order") order:String, //Сортировка
        @Query("type") type:String, //тип фильма
        @Query("ratingFrom") ratingFrom:Int, //минимальный рейтинг
        @Query("ratingTo") ratingTo:Int, // максимальный рейтинг
        @Query("yearFrom") yearFrom:Int, // минимальный год
        @Query("yearTo") yearTo:Int, //максимальный год
        @Query("keyword") keyword:String, // Search
        @Query("page") page:Int //номер страницы
    ):Response<Film>

    @GET(PREMIERE_URL)
    suspend fun getPremiere(
        @Query("year") year:Int,
        @Query("month") month: String
    ): Response<Premiere>

    @GET(RELEASE_URL)
    suspend fun getRelease(
        @Query("year") year:Int,
        @Query("month") month:String,
        @Query("page") page:Int
    ):Response<Release>

    @GET(FILTER_URL)
    suspend fun getFilter():Response<Filter>

    @GET(FILM_INFO_ID_URL)
    suspend fun getFilmInfo(
        @Path("id") id:Int
    ):Response<FilmInfo>

    @GET(SEASONS_ID_URL)
    suspend fun getSeason(
        @Path("id") id: Int
    ):Response<Season>
}