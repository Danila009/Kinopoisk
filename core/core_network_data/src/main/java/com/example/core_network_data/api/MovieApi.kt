package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.BUDGET_FILM_ID_URL
import com.example.core_network_data.common.ConstantsUrl.FACT_FILM_ID_URL
import com.example.core_network_data.common.ConstantsUrl.FILM_DISTRIBUTION_ID_URL
import com.example.core_network_data.common.ConstantsUrl.FILM_INFO_ID_URL
import com.example.core_network_data.common.ConstantsUrl.FILM_URL
import com.example.core_network_data.common.ConstantsUrl.FILTER_URL
import com.example.core_network_data.common.ConstantsUrl.IMAGE_ID_URL
import com.example.core_network_data.common.ConstantsUrl.MOVIE_AWARDS_URL
import com.example.core_network_data.common.ConstantsUrl.PREMIERE_URL
import com.example.core_network_data.common.ConstantsUrl.RELEASE_URL
import com.example.core_network_data.common.ConstantsUrl.REVIEW_URL
import com.example.core_network_data.common.ConstantsUrl.SEASONS_ID_URL
import com.example.core_network_data.common.ConstantsUrl.SEQUEL_AND_PREQUEL_ID_URL
import com.example.core_network_data.common.ConstantsUrl.SIMILAR_ID_URL
import com.example.core_network_data.common.ConstantsUrl.STAFF_URL
import com.example.core_network_data.common.ConstantsUrl.TRAILER_URL
import com.example.core_network_domain.model.movie.*
import com.example.core_network_domain.model.movie.award.Award
import com.example.core_network_domain.model.movie.budget.Budget
import com.example.core_network_domain.model.movie.distribution.Distribution
import com.example.core_network_domain.model.movie.fact.Fact
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.Release
import com.example.core_network_domain.model.movie.review.Review
import com.example.core_network_domain.model.movie.staff.Staff
import com.example.core_network_domain.model.movie.trailer.Trailer
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
    ): Film

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

    @GET(BUDGET_FILM_ID_URL)
    suspend fun getBudget(
        @Path("id") id: Int
    ):Response<Budget>

    @GET(FACT_FILM_ID_URL)
    suspend fun getFact(
        @Path("id") id: Int
    ):Response<Fact>

    @GET(STAFF_URL)
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

    @GET(FILM_DISTRIBUTION_ID_URL)
    suspend fun getDistribution(
        @Path("id") id: Int
    ):Response<Distribution>

    @GET(IMAGE_ID_URL)
    suspend fun getImage(
        @Path("id") id: Int,
        @Query("type") type:String,
        @Query("page") page:Int
    ):Response<ImageMovie>

    @GET(REVIEW_URL)
    suspend fun getReview(
        @Query("filmId") id:Int,
        @Query("page") page:Int
    ):Response<Review>

    @GET(TRAILER_URL)
    suspend fun getTrailer(
        @Path("id") id: Int
    ):Response<Trailer>

    @GET(MOVIE_AWARDS_URL)
    suspend fun getAwards(
        @Path("id") id: Int
    ):Response<Award>
}