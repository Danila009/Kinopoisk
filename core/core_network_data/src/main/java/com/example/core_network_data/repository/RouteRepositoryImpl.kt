package com.example.core_network_data.repository

import com.example.core_network_data.api.RouteApi
import com.example.core_network_domain.model.route.Route
import com.example.core_network_domain.repository.RouteRepository
import retrofit2.Response
import javax.inject.Inject

class RouteRepositoryImpl @Inject constructor(
    private val routeApi: RouteApi
): RouteRepository {
    override suspend fun getRoute(start: String, end: String): Response<Route> {
        return routeApi.getRoute(start = start, end = end)
    }
}