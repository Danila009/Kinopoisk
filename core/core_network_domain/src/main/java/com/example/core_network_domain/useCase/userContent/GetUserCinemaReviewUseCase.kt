package com.example.core_network_domain.useCase.userContent

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.cinema.ReviewItem
import com.example.core_network_domain.repository.UserContentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class GetUserCinemaReviewUseCase @Inject constructor(
    private val userContentRepository: UserContentRepository
): BaseApiResponse() {
    @ExperimentalSerializationApi
    operator fun invoke(): Flow<Response<List<ReviewItem>>> = flow {
        emit(safeApiCall { userContentRepository.getUserCinemaReview() })
    }
}