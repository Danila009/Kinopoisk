package com.example.core_network_data.repository

import android.util.Log
import com.example.core_network_data.api.MovieApi
import com.example.core_network_domain.model.movie.Film
import com.example.core_network_domain.model.movie.FilmInfo
import com.example.core_network_domain.model.movie.Filter
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.Release
import com.example.core_network_domain.model.serial.Season
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
): MovieRepository {
    override suspend fun getPremiere(year: Int, month: String): Premiere {
        return movieApi.getPremiere(year, month).body() ?: Premiere()
    }

    override suspend fun getRelease(year: Int, month: String, page: Int): Release {
        Log.e("Response:", movieApi.getRelease(year, month, page).code().toString())
        return movieApi.getRelease(year, month, page).body() ?: Release()
    }

    override suspend fun getFilm(
        genres: List<Int>,
        countries: List<Int>,
        order: String,
        type: String,
        ratingFrom: Int,
        ratingTo: Int,
        yearFrom: Int,
        yearTo: Int,
        keyword: String,
        page: Int,
    ): Film {
        return movieApi.getFilm(
            genres, countries, order, type, ratingFrom, ratingTo, yearFrom, yearTo, keyword, page
        ).body() ?: Film()
    }

    override suspend fun getFilter(): Filter {
        return movieApi.getFilter().body() ?: Filter()
    }

    override suspend fun getFilmInfo(id: Int): FilmInfo {
        return movieApi.getFilmInfo(id).body() ?: FilmInfo()
    }

    override suspend fun getSeason(id: Int): Season {
        return movieApi.getSeason(id).body() ?: Season()
    }

}