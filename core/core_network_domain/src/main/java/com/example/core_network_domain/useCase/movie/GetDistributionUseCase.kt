package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.distribution.Distribution
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetDistributionUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int): Distribution {
        return movieRepository.getDistribution(id)
    }
}