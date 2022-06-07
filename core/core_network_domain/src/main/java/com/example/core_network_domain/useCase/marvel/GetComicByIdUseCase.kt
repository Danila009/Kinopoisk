package com.example.core_network_domain.useCase.marvel

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_network_domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetComicByIdUseCase @Inject constructor(
    private val marvelRepository: MarvelRepository
):BaseApiResponse() {

    operator fun invoke(comicId: Int):Flow<Response<ComicsMarvel>> = flow {
        emit( safeApiCall { marvelRepository.getComicById(comicId) } )
    }
}