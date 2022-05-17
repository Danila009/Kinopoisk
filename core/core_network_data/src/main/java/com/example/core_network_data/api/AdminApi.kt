package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl
import com.example.core_network_domain.model.playlist.Playlist
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response
import retrofit2.http.GET

interface AdminApi {

    @ExperimentalSerializationApi
    @GET(ConstantsUrl.ADMIN_PLAYLIST_URL)
    suspend fun getAdminPlaylist(): Response<Playlist>
}