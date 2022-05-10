package com.example.core_network_domain.useCase.movie

import com.example.core_network_domain.model.movie.review.Review
import com.example.core_network_domain.repository.MovieRepository
import javax.inject.Inject

class GetReviewMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id:Int,page:Int):Review{
        return movieRepository.getReview(id, page)
    }
}