package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.Film
import com.example.core_network_domain.model.movie.review.ReviewDetail
import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.kinopoisk.api.model.topFilm.Top
import com.example.kinopoisk.common.Constants.FILM_URL
import com.example.kinopoisk.common.Constants.REVIEW_ID_URL
import com.example.kinopoisk.common.Constants.STAFF_INF0_ID_URL
import com.example.kinopoisk.common.Constants.TOP_FILM_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiKinopoisk {

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

    @GET(TOP_FILM_URL)
    suspend fun getTop(
        @Query("type") type: String,
        @Query("page") page:Int
    ):Response<Top>

    @GET(STAFF_INF0_ID_URL)
    suspend fun getStaffInfo(
        @Path("id") id: Int
    ):Response<StaffInfo>

    @GET(REVIEW_ID_URL)
    suspend fun getReviewDetail(
        @Query("reviewId") id: Int
    ):Response<ReviewDetail>

}