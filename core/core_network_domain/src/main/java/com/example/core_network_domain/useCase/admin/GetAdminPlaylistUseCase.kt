package com.example.core_network_domain.useCase.admin

import com.example.core_network_domain.apiResponse.BaseApiResponse
import com.example.core_network_domain.common.Response
import com.example.core_network_domain.model.playlist.Playlist
import com.example.core_network_domain.repository.AdminRepository
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class GetAdminPlaylistUseCase @Inject constructor(
    private val adminRepository: AdminRepository
):BaseApiResponse() {
    @ExperimentalSerializationApi
    suspend operator fun invoke():Response<Playlist>{
        return safeApiCall { adminRepository.getAdminPlaylist() }
    }
}