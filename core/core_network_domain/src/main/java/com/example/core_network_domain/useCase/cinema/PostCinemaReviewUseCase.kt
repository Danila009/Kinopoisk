package com.example.core_network_domain.useCase.cinema

import com.example.core_network_domain.model.cinema.CinemaAddReview
import com.example.core_network_domain.repository.CinemaRepository
import javax.inject.Inject

class PostCinemaReviewUseCase @Inject constructor(
    private val cinemaRepository: CinemaRepository
) {
    suspend operator fun invoke(cinemaId:Int,review:CinemaAddReview){
        cinemaRepository.postCinemaReview(cinemaId, review)
    }
}