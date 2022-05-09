package com.example.core_network_domain.useCase.marvel

import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_network_domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetComicsMarvelUseCase @Inject constructor(
    private val marvelRepository: MarvelRepository
) {
    operator fun invoke(search:String,offset:Int = 1):Flow<Response<ComicsMarvel>> = flow {
        try {
            val response = marvelRepository.getComics(
                search = search.ifEmpty { null },
                offset = offset
            )
            response?.let {
                emit(Response.Success(data = response))
            }
        }catch (e:Exception){
            emit(Response.Error<ComicsMarvel>(message = e.message.toString()))
        }
    }
}