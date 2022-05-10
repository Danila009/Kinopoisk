package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.Similar
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetSimilarUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int):Similar{
        return movieRepository.getSimilar(id)
    }
}