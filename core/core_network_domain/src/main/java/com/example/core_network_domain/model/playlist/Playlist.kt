package com.example.core_network_domain.model.playlist

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class Playlist(
    val total:Int = 0,
    val items:List<PlaylistItem> = listOf()
)
