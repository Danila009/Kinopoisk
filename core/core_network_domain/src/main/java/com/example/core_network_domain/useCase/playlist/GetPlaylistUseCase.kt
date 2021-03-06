package com.example.core_network_domain.useCase.playlist

import com.example.core_network_domain.model.playlist.PlaylistItem
import com.example.core_network_domain.repository.PlaylistRepository
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

@ExperimentalSerializationApi
class GetPlaylistUseCase @Inject constructor(
    private val playlistRepository: PlaylistRepository
) {
    suspend operator fun invoke():List<PlaylistItem> {
        return playlistRepository.getPlaylist()
    }
}