package com.example.core_network_domain.useCase.history

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class GetHistoryMovieUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
):BaseApiResponse() {
    @ExperimentalSerializationApi
    operator fun invoke():Flow<Response<HistoryMovie>> = flow{
        emit(safeApiCall { historyRepository.getHistoryMovie() })
    }
}