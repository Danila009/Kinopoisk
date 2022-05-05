package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.premiere.Release
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetReleaseUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(year:Int, month: String, page:Int): Release {
        return movieRepository.getRelease(year, month, page)
    }
}