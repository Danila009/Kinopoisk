package com.example.core_network_domain.repository

import com.example.core_network_domain.model.movie.video.Video

interface MovieVideoRepository {

    suspend fun getVideoMovie(
        kinopoiskId:Int
    ): Video
}