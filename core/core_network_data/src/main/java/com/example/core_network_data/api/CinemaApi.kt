package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.CINEMA_ADD_REVIEW_URL
import com.example.core_network_data.common.ConstantsUrl.CINEMA_PHONE_URL
import com.example.core_network_data.common.ConstantsUrl.CINEMA_PHOTO_URL
import com.example.core_network_data.common.ConstantsUrl.CINEMA_REVIEW_URL
import com.example.core_network_data.common.ConstantsUrl.CINEMA_SCHEDULE_URL
import com.example.core_network_data.common.ConstantsUrl.CINEMA_URL
import com.example.core_network_domain.model.cinema.*
import retrofit2.Response
import retrofit2.http.*

interface CinemaApi {

    @GET(CINEMA_URL)
    suspend fun getCinema(
        @Query("search") search:String,
        @Query("Has3D") has3D:Boolean?,
        @Query("Has4D") has4D:Boolean?,
        @Query("HasImax") hasImax:Boolean?
    ):Response<List<Cinema>>

    @GET("$CINEMA_URL/{id}")
    suspend fun getCinemaById(
        @Path("id") id:Int
    ):Response<Cinema>

    @GET(CINEMA_PHOTO_URL)
    suspend fun getCinemaPhotos(
        @Path("id") id:Int
    ):Response<List<Photo>>

    @GET(CINEMA_PHONE_URL)
    suspend fun getCinemaPhone(
        @Path("id") id:Int
    ):Response<List<Phone>>

    @GET(CINEMA_SCHEDULE_URL)
    suspend fun getCinemaSchedule(
        @Path("id") id:Int
    ):Response<List<Schedule>>

    @GET(CINEMA_REVIEW_URL)
    suspend fun getCinemaReview(
        @Path("cinemaId") id:Int,
        @Query("search") search:String,
        @Query("startDate") startDate:String?,
        @Query("endDate") endDate:String?,
        @Query("startReting") startRating:Float?,
        @Query("endRating") endRating:Float?
    ):Response<Review>

    @POST(CINEMA_ADD_REVIEW_URL)
    suspend fun postCinemaReview(
        @Path("cinemaId") cinemaId:Int,
        @Body review:CinemaAddReview
    )

}