package com.example.core_network_domain.useCase.IMDb

import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.IMDb.award.Award
import com.example.core_network_domain.repository.IMDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmAwardUseCase @Inject constructor(
    private val imDbRepository: IMDbRepository
) {
    operator fun invoke(id:String):Flow<Response<Award>> = flow {
        try {
            val response = imDbRepository.getFilmAward(id)
            response?.let {
                emit(Response.Success(data = response))
            }
            emit(Response.Loading<Award>())
        }catch (e:Exception){
            emit(Response.Error<Award>(message = e.message.toString()))
        }
    }
}