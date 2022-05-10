package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.SequelAndPrequel
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetSequelAndPrequelUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int):List<SequelAndPrequel>{
        return movieRepository.getSequelAndPrequel(id)
    }
}