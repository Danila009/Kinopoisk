package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.MOVIE_VIDEO_URL
import com.example.core_network_domain.model.movie.video.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieVideoApi {

    @GET(MOVIE_VIDEO_URL)
    suspend fun getMovieVideo(
        @Path("kimoPoiskId") kinopoiskId:Int
    ):Response<Video>
}