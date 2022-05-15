package com.example.core_network_data.repository

import com.example.core_network_data.api.HistoryApi
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.model.movie.history.HistoryMovieItem
import com.example.core_network_domain.model.movie.history.HistorySearch
import com.example.core_network_domain.model.movie.history.HistorySearchItem
import com.example.core_network_domain.repository.HistoryRepository
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val historyApi: HistoryApi
): HistoryRepository {

    @ExperimentalSerializationApi
    override suspend fun getHistoryMovie(): Response<HistoryMovie> {
        return historyApi.getHistoryMovie()
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
    override suspend fun getHistorySearch(): Response<HistorySearch> {
        return historyApi.getHistorySearch()
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