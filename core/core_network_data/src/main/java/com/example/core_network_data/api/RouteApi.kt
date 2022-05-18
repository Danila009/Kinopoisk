package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.ROUTE_URL
import com.example.core_network_domain.model.route.Route
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RouteApi {

    @GET(ROUTE_URL)
    suspend fun getRoute(
        @Path("profile") profile:String = "driving-car",
        @Query("api_key") api_key:String = "5b3ce3597851110001cf6248590a5b15165142929ec3ca6cccdfbe77",
        @Query("start") start:String,
        @Query("end") end:String
    ): Response<Route>

}