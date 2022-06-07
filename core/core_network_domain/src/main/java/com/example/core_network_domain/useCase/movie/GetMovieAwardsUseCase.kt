package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.award.Award
import com.example.core_network_domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieAwardsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
):BaseApiResponse() {

    operator fun invoke(id:Int):Flow<Response<Award>> = flow {
        emit( safeApiCall { movieRepository.getAwards(id) } )
    }
}