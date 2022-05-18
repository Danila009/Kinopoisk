package com.example.core_network_domain.repository

import com.example.core_network_domain.model.route.Route
import retrofit2.Response

interface RouteRepository {

    suspend fun getRoute(
        start:String,
        end:String
    ):Response<Route>
}