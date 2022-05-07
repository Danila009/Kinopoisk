package com.example.core_network_domain.useCase.history

import com.example.core_network_domain.repository.HistoryRepository
import javax.inject.Inject

class DeleteHistoryMovieUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke() = historyRepository.deleteHistoryMovie()

    suspend operator fun invoke(id:Int) = historyRepository.deleteHistoryMovie(id)
}