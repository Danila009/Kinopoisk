package com.example.core_network_domain.useCase.cinema

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.repository.CinemaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCinemaUseCase @Inject constructor(
    private val cinemaRepository: CinemaRepository
):BaseApiResponse() {
    operator fun invoke(
        search:String,
        has3D:Boolean?,
        has4D:Boolean?,
        hasImax:Boolean?
    ):Flow<Response<List<Cinema>>> = flow {
        emit( safeApiCall { cinemaRepository.getCinema(search, has3D, has4D, hasImax) } )
    }
}