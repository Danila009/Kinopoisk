package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.PLAYLIST_URL
import com.example.core_network_domain.model.playlist.PlaylistItem
import com.example.core_network_domain.model.playlist.PlaylistAdd
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

@ExperimentalSerializationApi
interface PlaylistApi {

    @GET(PLAYLIST_URL)
    suspend fun getPlaylist():Response<List<PlaylistItem>>

    @POST(PLAYLIST_URL)
    suspend fun postPlaylist(
        @Body playlistAdd: PlaylistAdd
    )
}