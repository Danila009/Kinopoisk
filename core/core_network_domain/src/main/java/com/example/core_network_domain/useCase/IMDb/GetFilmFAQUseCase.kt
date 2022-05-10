package com.example.core_network_domain.useCase.IMDb

import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.IMDb.FAQ.FAQ
import com.example.core_network_domain.repository.IMDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmFAQUseCase @Inject constructor(
    private val imDbRepository: IMDbRepository
) {
    operator fun invoke(id:String):Flow<Response<FAQ>> = flow {
        try {
            val response = imDbRepository.getFilmFAQ(id)
            response?.let {
                emit(Response.Success(data = response))
            }
            emit(Response.Loading<FAQ>())
        }catch (e:Exception){
            emit(Response.Error<FAQ>(message = e.message.toString()))
        }
    }
}