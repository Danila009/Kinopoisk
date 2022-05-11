package com.example.core_network_domain.useCase.cinema

import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.Review
import com.example.core_network_domain.repository.CinemaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCinemaReviewUseCase @Inject constructor(
    private val cinemaRepository: CinemaRepository
) {
    operator fun invoke(
        id: Int,
        search: String,
        startDate:String?,
        endDate:String?,
        startRating:Float?,
        endRating:Float?
    ):Flow<Response<Review>> = flow {
        try {
            val response = cinemaRepository.getCinemaReview(
                id, search, startDate, endDate, startRating, endRating
            )
            response?.let {
                emit(Response.Success(data = it))
            }
        }catch (e:Exception){
            emit(Response.Error<Review>(message = e.message.toString()))
        }
    }
}