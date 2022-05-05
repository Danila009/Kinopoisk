package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.CINEMA_URL
import com.example.core_network_domain.model.cinema.Cinema
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CinemaApi {

    @GET(CINEMA_URL)
    suspend fun getCinema(
        @Query("search") search:String,
        @Query("Has3D") has3D:Boolean?,
        @Query("Has4D") has4D:Boolean?,
        @Query("HasImax") hasImax:Boolean?
    ):Response<List<Cinema>>
}