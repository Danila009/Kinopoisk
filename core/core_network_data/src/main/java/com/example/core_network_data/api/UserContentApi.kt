package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.USER_FAVORITE_MOVIE_URL
import com.example.core_network_data.common.ConstantsUrl.USER_FAVORITE_STAFF_URL
import com.example.core_network_data.common.ConstantsUrl.USER_WATCH_LATER_URL
import com.example.core_network_domain.model.movie.Movie
import com.example.core_network_domain.model.staff.Staff
import retrofit2.Response
import retrofit2.http.GET

interface UserContentApi {

    @GET(USER_FAVORITE_MOVIE_URL)
    suspend fun getUserFavoriteMovie():Response<Movie>

    @GET(USER_WATCH_LATER_URL)
    suspend fun getUserWatchLater():Response<Movie>

    @GET(USER_FAVORITE_STAFF_URL)
    suspend fun getUserFavoriteStaff():Response<Staff>
}