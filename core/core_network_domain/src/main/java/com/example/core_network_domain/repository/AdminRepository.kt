package com.example.core_network_domain.repository

import com.example.core_network_domain.model.playlist.Playlist
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response

interface AdminRepository {

    @ExperimentalSerializationApi
    suspend fun getAdminPlaylist(): Response<Playlist>
}