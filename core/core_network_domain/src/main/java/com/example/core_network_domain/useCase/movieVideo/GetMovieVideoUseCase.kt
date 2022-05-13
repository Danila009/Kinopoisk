package com.example.core_network_domain.useCase.movieVideo

import com.example.core_network_domain.model.movie.video.Video
import com.example.core_network_domain.repository.MovieVideoRepository
import javax.inject.Inject

class GetMovieVideoUseCase @Inject constructor(
    private val movieVideoRepository: MovieVideoRepository
) {
    suspend operator fun invoke(kinopoiskId:Int):Video{
        return movieVideoRepository.getVideoMovie(kinopoiskId)
    }
}