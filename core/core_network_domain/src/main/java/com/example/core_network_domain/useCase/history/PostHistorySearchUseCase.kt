package com.example.core_network_domain.useCase.history

import com.example.core_network_domain.model.movie.history.HistorySearchItem
import com.example.core_network_domain.repository.HistoryRepository
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class PostHistorySearchUseCase @Inject constructor(
    private val historyRepository: HistoryRepository
) {
    @ExperimentalSerializationApi
    suspend operator fun invoke(historySearchItem: HistorySearchItem){
        historyRepository.postHistorySearch(historySearchItem)
    }
}