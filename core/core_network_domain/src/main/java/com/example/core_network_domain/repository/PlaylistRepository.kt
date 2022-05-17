package com.example.core_network_domain.repository

import com.example.core_network_domain.model.playlist.PlaylistItem
import com.example.core_network_domain.model.playlist.PlaylistAdd
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
interface PlaylistRepository {

    suspend fun getPlaylist():List<PlaylistItem>

    suspend fun postPlaylist(playlistAdd: PlaylistAdd)
}