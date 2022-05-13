package com.example.core_network_data.repository

import com.example.core_network_data.api.MovieVideoApi
import com.example.core_network_domain.model.movie.video.Video
import com.example.core_network_domain.repository.MovieVideoRepository
import javax.inject.Inject

class MovieVideoRepositoryImpl @Inject constructor(
    private val movieVideoApi: MovieVideoApi
): MovieVideoRepository {
    override suspend fun getVideoMovie(kinopoiskId: Int): Video {
        return movieVideoApi.getMovieVideo(kinopoiskId).body() ?: Video()
    }
}