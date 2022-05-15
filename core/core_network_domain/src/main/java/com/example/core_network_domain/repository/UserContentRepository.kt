package com.example.core_network_domain.repository

import com.example.core_network_domain.model.cinema.ReviewItem
import com.example.core_network_domain.model.movie.Movie
import com.example.core_network_domain.model.staff.Staff
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response

interface UserContentRepository {

    suspend fun getUserFavoriteMovie():Movie

    suspend fun getUserWatchLater():Movie

    suspend fun getUserFavoriteStaff():Staff

    @ExperimentalSerializationApi
    suspend fun getUserCinemaReview():Response<List<ReviewItem>>
}