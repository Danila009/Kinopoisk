package com.example.core_network_domain.useCase.route

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.route.Route
import com.example.core_network_domain.repository.RouteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRouteUseCase @Inject constructor(
    private val routeRepository: RouteRepository
):BaseApiResponse() {

    operator fun invoke(start:String, end:String):Flow<Response<Route>> = flow{
        emit( safeApiCall { routeRepository.getRoute(start, end) } )
    }
}