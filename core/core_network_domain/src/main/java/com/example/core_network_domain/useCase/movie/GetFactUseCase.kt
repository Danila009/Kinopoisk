package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.fact.Fact
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetFactUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int):Fact {
        return movieRepository.getFact(id)
    }
}