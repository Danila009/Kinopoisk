package com.example.core_network_data.repository

import com.example.core_network_data.api.MovieApi
import com.example.core_network_domain.model.movie.*
import com.example.core_network_domain.model.movie.budget.Budget
import com.example.core_network_domain.model.movie.distribution.Distribution
import com.example.core_network_domain.model.movie.fact.Fact
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.Release
import com.example.core_network_domain.model.movie.review.Review
import com.example.core_network_domain.model.movie.staff.Staff
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
        )
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

    override suspend fun getBudget(id: Int): Budget {
        return movieApi.getBudget(id).body() ?: Budget()
    }

    override suspend fun getFact(id: Int): Fact {
        return movieApi.getFact(id).body() ?: Fact()
    }

    override suspend fun getStaff(id: Int): List<Staff> {
        return movieApi.getStaff(id).body() ?: emptyList()
    }

    override suspend fun getSimilar(id: Int): Similar {
        return movieApi.getSimilar(id).body() ?: Similar()
    }

    override suspend fun getSequelAndPrequel(id: Int): List<SequelAndPrequel> {
        return movieApi.getSequelAndPrequel(id).body() ?: emptyList()
    }

    override suspend fun getDistribution(id: Int): Distribution {
        return movieApi.getDistribution(id).body() ?: Distribution()
    }

    override suspend fun getImage(id: Int, type: String, page: Int): ImageMovie {
        return movieApi.getImage(id, type, page).body() ?: ImageMovie()
    }

    override suspend fun getReview(id: Int, page: Int): Review {
        return movieApi.getReview(id, page).body() ?: Review()
    }

}