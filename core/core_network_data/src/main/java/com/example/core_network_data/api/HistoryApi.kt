package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.HISTORY_MOVIE_URL
import com.example.core_network_data.common.ConstantsUrl.HISTORY_SEARCH_URL
import com.example.core_network_domain.model.movie.history.HistoryMovie
import com.example.core_network_domain.model.movie.history.HistoryMovieItem
import com.example.core_network_domain.model.movie.history.HistorySearch
import com.example.core_network_domain.model.movie.history.HistorySearchItem
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface HistoryApi{

    @ExperimentalSerializationApi
    @GET(HISTORY_MOVIE_URL)
    suspend fun getHistoryMovie():Response<HistoryMovie>

    @ExperimentalSerializationApi
    @POST(HISTORY_MOVIE_URL)
    suspend fun postHistoryMovie(
        @Body historyMovieItem: HistoryMovieItem
    )

    @DELETE(HISTORY_MOVIE_URL)
    suspend fun deleteHistoryMovie():Response<Unit>

    @DELETE("$HISTORY_MOVIE_URL/id")
    suspend fun deleteHistoryMovie(id:Int)

    @ExperimentalSerializationApi
    @GET(HISTORY_SEARCH_URL)
    suspend fun getHistorySearch():Response<HistorySearch>

    @ExperimentalSerializationApi
    @POST(HISTORY_SEARCH_URL)
    suspend fun postHistorySearch(
        @Body historySearchItem: HistorySearchItem
    )

    @DELETE(HISTORY_SEARCH_URL)
    suspend fun deleteHistorySearch():Response<Unit>

    @DELETE("$HISTORY_SEARCH_URL/id")
    suspend fun deleteHistorySearch(id:Int)
}