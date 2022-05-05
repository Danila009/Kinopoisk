package com.example.core_network_domain.repository

import com.example.core_network_domain.model.playlist.Playlist
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
interface PlaylistRepository {

    suspend fun getPlaylist():List<Playlist>
}