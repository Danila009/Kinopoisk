package com.example.core_network_domain.model.playlist

import com.example.core_network_domain.model.movie.MovieItem
import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.Serializable

@Serializable
data class PlaylistAdd(
    val title:String,
    val description:String,
    @Serializable(with = DateTimeSerialization::class)
    val date:String,
    val movie:List<MovieItem>
)