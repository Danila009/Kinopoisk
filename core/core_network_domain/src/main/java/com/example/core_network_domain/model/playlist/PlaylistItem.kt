package com.example.core_network_domain.model.playlist

import com.example.core_network_domain.model.user.User
import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class PlaylistItem(
    val id:Int,
    val title:String,
    @Serializable(with = DateTimeSerialization::class)
    val date:String,
    val totalMovie:Int,
    val totalPlaylistSubscription:Int,
    val adminUsers:List<User>
)