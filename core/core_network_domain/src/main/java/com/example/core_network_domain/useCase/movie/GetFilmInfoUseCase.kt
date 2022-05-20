package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFilmInfoUseCase @Inject constructor(
    private val movieRepository: MovieRepository
):BaseApiResponse() {
    operator fun invoke(id:Int):Flow<Response<FilmInfo>> = flow {
        emit( safeApiCall { movieRepository.getFilmInfo(id) } )
    }
}