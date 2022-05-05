package com.example.core_network_data.repository

import com.example.core_network_data.api.CinemaApi
import com.example.core_network_domain.model.cinema.Cinema
import com.example.core_network_domain.repository.CinemaRepository
import javax.inject.Inject

class CinemaRepositoryImpl @Inject constructor(
    private val cinemaApi: CinemaApi
): CinemaRepository {
    override suspend fun getCinema(
        search: String,
        has3D: Boolean?,
        has4D: Boolean?,
        hasImax: Boolean?,
    ): List<Cinema> {
        return cinemaApi.getCinema(search, has3D, has4D, hasImax).body()!!
    }
}