package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.PREMIERE_URL
import com.example.core_network_data.common.ConstantsUrl.RELEASE_URL
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.Release
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

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
}