package com.example.core_network_domain.useCase.history

import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.repository.HistoryRepository
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class GetHistoryMovieUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    @ExperimentalSerializationApi
    suspend operator fun invoke():HistoryMovie = historyRepository.getHistoryMovie()
}