package com.example.core_network_domain.model.playlist

import com.example.core_network_domain.model.movie.MovieItem
import com.example.core_network_domain.model.user.User
import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class Playlist(
    val id:Int,
    val title:String,
    @Serializable(with = DateTimeSerialization::class)
    val date:String,
    val totalMovie:Int,
    val totalPlaylistSubscription:Int,
    val adminUsers:List<User>,
    val movies:List<MovieItem>
)