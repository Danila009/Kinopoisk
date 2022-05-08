package com.example.core_network_domain.useCase.rickAndMorty

import com.example.core_network_domain.model.rickAndMorty.Episode
import com.example.core_network_domain.repository.RickAndMortyRepository
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) {
    suspend operator fun invoke(
        name:String,
        episode:Int,
        page:Int
    ):Episode = rickAndMortyRepository.getEpisodes(
        name, episode, page
    )
}