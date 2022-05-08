package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.serial.Season
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetSeasonUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int): Season {
        return movieRepository.getSeason(id)
    }
}