package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.PLAYLIST_URL
import com.example.core_network_domain.model.playlist.Playlist
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Response
import retrofit2.http.GET

@ExperimentalSerializationApi
interface PlaylistApi {

    @GET(PLAYLIST_URL)
    suspend fun getPlaylist():Response<List<Playlist>>
}