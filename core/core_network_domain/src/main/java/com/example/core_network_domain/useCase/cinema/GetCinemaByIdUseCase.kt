package com.example.core_network_domain.useCase.cinema

import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.repository.CinemaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCinemaByIdUseCase @Inject constructor(
    private val cinemaRepository: CinemaRepository
) {
    operator fun invoke(id:Int):Flow<Response<Cinema>> = flow {
        try {
            val response = cinemaRepository.getCinemaById(id)
            response?.let {
                emit(Response.Success(data = it))
            }
//            emit(Response.Loading<Cinema>())
        }catch (e:Exception){
            emit(Response.Error<Cinema>(message = e.message.toString()))
        }
    }
}