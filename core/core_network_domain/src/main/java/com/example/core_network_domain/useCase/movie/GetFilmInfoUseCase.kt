package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetFilmInfoUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int):FilmInfo{
        return movieRepository.getFilmInfo(id)
    }
}