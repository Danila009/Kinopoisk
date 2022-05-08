package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.Filter
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetFilterUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke():Filter = movieRepository.getFilter()
}