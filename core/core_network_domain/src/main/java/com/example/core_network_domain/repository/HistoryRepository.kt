package com.example.core_network_domain.repository

import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.model.movie.history.HistoryMovieItem
import com.example.core_network_domain.model.movie.history.HistorySearch
import com.example.core_network_domain.model.movie.history.HistorySearchItem
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response

interface HistoryRepository {

    @ExperimentalSerializationApi
    suspend fun getHistoryMovie(): Response<HistoryMovie>

    @ExperimentalSerializationApi
    suspend fun postHistoryMovie(historyMovieItem: HistoryMovieItem)

    suspend fun deleteHistoryMovie()

    suspend fun deleteHistoryMovie(id:Int)

    @ExperimentalSerializationApi
    suspend fun getHistorySearch(): Response<HistorySearch>

    @ExperimentalSerializationApi
    suspend fun postHistorySearch(
        historySearchItem: HistorySearchItem
    )

    suspend fun deleteHistorySearch()

    suspend fun deleteHistorySearch(id: Int)
}