package com.example.core_network_data.repository

import com.example.core_network_data.api.AdminApi
import com.example.core_network_domain.model.playlist.Playlist
import com.example.core_network_domain.repository.AdminRepository
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response
import javax.inject.Inject

class AdminRepositoryImpl @Inject constructor(
    private val adminApi: AdminApi
): AdminRepository {
    @ExperimentalSerializationApi
    override suspend fun getAdminPlaylist(): Response<Playlist> {
        return adminApi.getAdminPlaylist()
    }
}