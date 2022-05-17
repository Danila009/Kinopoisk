package com.example.core_network_domain.useCase.playlist

import com.example.core_network_domain.model.playlist.PlaylistAdd
import com.example.core_network_domain.repository.PlaylistRepository
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class PostPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    suspend operator fun invoke(playlistAdd: PlaylistAdd){
        playlistRepository.postPlaylist(playlistAdd)
    }
}