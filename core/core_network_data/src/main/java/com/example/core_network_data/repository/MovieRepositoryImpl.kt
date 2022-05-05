package com.example.core_network_data.repository

import com.example.core_network_data.api.MovieApi
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.Release
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
): MovieRepository {
    override suspend fun getPremiere(year: Int, month: String): Premiere {
        return movieApi.getPremiere(year, month).body()!!
    }

    override suspend fun getRelease(year: Int, month: String, page: Int): Release {
        return movieApi.getRelease(year, month, page).body()!!
    }
}