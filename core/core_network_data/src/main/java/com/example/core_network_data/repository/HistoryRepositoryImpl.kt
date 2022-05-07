package com.example.core_network_data.repository

import com.example.core_network_data.api.HistoryApi
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.model.movie.history.HistoryMovieItem
import com.example.core_network_domain.model.movie.history.HistorySearch
import com.example.core_network_domain.model.movie.history.HistorySearchItem
import com.example.core_network_domain.repository.HistoryRepository
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyApi: HistoryApi
): HistoryRepository {

    @ExperimentalSerializationApi
    override suspend fun getHistoryMovie(): HistoryMovie {
        return historyApi.getHistoryMovie().body() ?: HistoryMovie()
    }

    @ExperimentalSerializationApi
    override suspend fun postHistoryMovie(historyMovieItem: HistoryMovieItem) {
       historyApi.postHistoryMovie(historyMovieItem)
    }

    override suspend fun deleteHistoryMovie() {
        historyApi.deleteHistoryMovie()
    }

    override suspend fun deleteHistoryMovie(id: Int) {
        historyApi.deleteHistoryMovie(id)
    }

    @ExperimentalSerializationApi
    override suspend fun getHistorySearch(): HistorySearch {
        return historyApi.getHistorySearch().body() ?: HistorySearch()
    }

    @ExperimentalSerializationApi
    override suspend fun postHistorySearch(historySearchItem: HistorySearchItem) {
        historyApi.postHistorySearch(historySearchItem)
    }

    override suspend fun deleteHistorySearch() {
        historyApi.deleteHistorySearch()
    }

    override suspend fun deleteHistorySearch(id: Int) {
        historyApi.deleteHistorySearch(id)
    }
}