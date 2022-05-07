package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.Film
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetFilmUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        genres:List<Int>,
        countries:List<Int>,
        order:String,
        type:String,
        ratingFrom:Int,
        ratingTo:Int,
        yearFrom:Int,
        yearTo:Int,
        keyword:String,
        page:Int
    ):Film{
        return movieRepository.getFilm(
            genres, countries, order, type, ratingFrom, ratingTo, yearFrom, yearTo, keyword, page
        )
    }
}