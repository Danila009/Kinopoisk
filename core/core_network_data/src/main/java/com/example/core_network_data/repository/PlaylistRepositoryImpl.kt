package com.example.core_network_data.repository

import com.example.core_network_data.api.PlaylistApi
import com.example.core_network_domain.model.playlist.Playlist
import com.example.core_network_domain.repository.PlaylistRepository
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class PlaylistRepositoryImpl @Inject constructor(
    private val playlistApi: PlaylistApi
): PlaylistRepository {
    override suspend fun getPlaylist(): List<Playlist> {
        return playlistApi.getPlaylist().body()!!
    }
}