package com.example.core_network_data.repository

import com.example.core_network_data.api.UserContentApi
import com.example.core_network_domain.model.cinema.ReviewItem
import com.example.core_network_domain.model.movie.Movie
import com.example.core_network_domain.model.staff.Staff
import com.example.core_network_domain.repository.UserContentRepository
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response
import javax.inject.Inject

class UserContentRepositoryImpl @Inject constructor(
    private val userContentApi: UserContentApi
): UserContentRepository {

    override suspend fun getUserFavoriteMovie(): Movie {
        return userContentApi.getUserFavoriteMovie().body() ?: Movie()
    }

    override suspend fun getUserWatchLater(): Movie {
        return userContentApi.getUserWatchLater().body() ?: Movie()
    }

    override suspend fun getUserFavoriteStaff(): Staff {
        return userContentApi.getUserFavoriteStaff().body() ?: Staff()
    }

    @ExperimentalSerializationApi
    override suspend fun getUserCinemaReview(): Response<List<ReviewItem>> {
        return userContentApi.getUserCinemaReview()
    }
}