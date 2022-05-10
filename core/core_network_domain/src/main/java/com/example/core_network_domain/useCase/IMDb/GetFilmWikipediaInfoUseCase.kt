package com.example.core_network_domain.useCase.IMDb

import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.IMDb.wikipedia.Wikipedia
import com.example.core_network_domain.repository.IMDbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmWikipediaInfoUseCase @Inject constructor(
    private val imDbRepository: IMDbRepository
) {
    operator fun invoke(id:String):Flow<Response<Wikipedia>> = flow {
        try {
            val response = imDbRepository.getFilmWikipediaInfo(id)
            response?.let {
                emit(Response.Success(it))
            }
            emit(Response.Loading<Wikipedia>())
        }catch (e:Exception){
            emit(Response.Error<Wikipedia>(message = e.message.toString()))
        }
    }
}