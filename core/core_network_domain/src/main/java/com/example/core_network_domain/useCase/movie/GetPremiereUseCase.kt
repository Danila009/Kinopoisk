package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetPremiereUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(year:Int, month: String): Premiere {
        return movieRepository.getPremiere(year, month)
    }
}