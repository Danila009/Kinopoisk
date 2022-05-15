package com.example.core_network_data.repository

import com.example.core_network_data.api.CinemaApi
import com.example.core_network_domain.model.cinema.*
import com.example.core_network_domain.repository.CinemaRepository
import retrofit2.Response
import javax.inject.Inject

class CinemaRepositoryImpl @Inject constructor(
    private val cinemaApi: CinemaApi
): CinemaRepository {
    override suspend fun getCinema(
        search: String,
        has3D: Boolean?,
        has4D: Boolean?,
        hasImax: Boolean?,
    ): Response<List<Cinema>> {
        return cinemaApi.getCinema(search, has3D, has4D, hasImax)
    }

    override suspend fun getCinemaById(id: Int): Cinema? {
        return cinemaApi.getCinemaById(id).body()
    }

    override suspend fun getCinemaPhotos(id: Int): List<Photo> {
        return cinemaApi.getCinemaPhotos(id).body() ?: emptyList()
    }

    override suspend fun getCinemaPhone(id: Int): List<Phone> {
        return cinemaApi.getCinemaPhone(id).body() ?: emptyList()
    }

    override suspend fun getCinemaSchedule(id: Int): List<Schedule> {
        return cinemaApi.getCinemaSchedule(id).body() ?: emptyList()
    }

    override suspend fun getCinemaReview(
        id: Int,
        search: String,
        startDate: String?,
        endDate: String?,
        startRating: Float?,
        endRating: Float?,
    ): Review? {
        return cinemaApi.getCinemaReview(
            id = id,
            search = search,
            startDate = startDate,
            endDate = endDate,
            startRating = startRating,
            endRating = endRating
        ).body()
    }

    override suspend fun postCinemaReview(cinemaId: Int, review: CinemaAddReview) {
        cinemaApi.postCinemaReview(cinemaId, review)
    }
}