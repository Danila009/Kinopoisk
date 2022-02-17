package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.Film
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.filmInfo.*
import com.example.kinopoisk.api.model.filmInfo.distribution.Distribution
import com.example.kinopoisk.api.model.premiere.Premiere
import com.example.kinopoisk.api.model.premiere.Release
import com.example.kinopoisk.api.model.review.Review
import com.example.kinopoisk.api.model.review.ReviewDetail
import com.example.kinopoisk.api.model.seasons.Season
import com.example.kinopoisk.api.model.staff.Staff
import com.example.kinopoisk.api.model.staff.StaffInfo
import com.example.kinopoisk.api.model.topFilm.Top
import com.example.kinopoisk.utils.Constants.BUDGET_FILM_ID_URL
import com.example.kinopoisk.utils.Constants.FACT_FILM_ID_URL
import com.example.kinopoisk.utils.Constants.FILM_DISTRIBUTION_ID_URL
import com.example.kinopoisk.utils.Constants.FILM_INFO_ID_URL
import com.example.kinopoisk.utils.Constants.FILM_URL
import com.example.kinopoisk.utils.Constants.IMAGE_ID_URL
import com.example.kinopoisk.utils.Constants.PREMIERE_URL
import com.example.kinopoisk.utils.Constants.RELEASE_URL
import com.example.kinopoisk.utils.Constants.REVIEW_ID_URL
import com.example.kinopoisk.utils.Constants.REVIEW_URL
import com.example.kinopoisk.utils.Constants.SEASONS_ID_URL
import com.example.kinopoisk.utils.Constants.SEQUEL_AND_PREQUEL_ID_URL
import com.example.kinopoisk.utils.Constants.SIMILAR_ID_URL
import com.example.kinopoisk.utils.Constants.STAFF_INF0_ID_URL
import com.example.kinopoisk.utils.Constants.STAFf_URL
import com.example.kinopoisk.utils.Constants.TOP_FILM_URL
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

    @GET(SEQUEL_AND_PREQUEL_ID_URL)
    suspend fun getSequelAndPrequel(
        @Path("id") id: Int
    ):Response<List<SequelAndPrequel>>

    @GET(SEASONS_ID_URL)
    suspend fun getSeason(
        @Path("id") id: Int
    ):Response<Season>

    @GET(PREMIERE_URL)
    suspend fun getPremiere(
        @Query("year") year:Int,
        @Query("month") month: String
    ):Response<Premiere>

    @GET(RELEASE_URL)
    suspend fun getRelease(
        @Query("year") year:Int,
        @Query("month") month:String,
        @Query("page") page:Int
    ):Response<Release>

    @GET(TOP_FILM_URL)
    suspend fun getTop(
        @Query("type") type: String,
        @Query("page") page:Int
    ):Response<Top>

    @GET(IMAGE_ID_URL)
    suspend fun getImage(
        @Path("id") id: Int,
        @Query("type") type:String,
        @Query("page") page:Int
    ):Response<Image>

    @GET(REVIEW_URL)
    suspend fun getReview(
        @Query("filmId") id:Int,
        @Query("page") page:Int
    ):Response<Review>

    @GET(STAFF_INF0_ID_URL)
    suspend fun getStaffInfo(
        @Path("id") id: Int
    ):Response<StaffInfo>

    @GET(REVIEW_ID_URL)
    suspend fun getReviewDetail(
        @Query("reviewId") id: Int
    ):Response<ReviewDetail>

    @GET(FILM_DISTRIBUTION_ID_URL)
    suspend fun getDistribution(
        @Path("id") id: Int
    ):Response<Distribution>
}