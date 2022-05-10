package com.example.core_network_domain.model.movie.history

import com.example.core_network_domain.model.movie.MovieItem
import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class HistoryMovieItem(
    val id:Int?,
    @Serializable(with = DateTimeSerialization::class)
    val date:String,
    val movie: MovieItem
)