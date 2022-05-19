package com.example.core_network_data.repository

import com.example.core_network_data.api.RickAndMortyApi
import com.example.core_network_domain.model.rickAndMorty.*
import com.example.core_network_domain.repository.RickAndMortyRepository
import retrofit2.Response
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
): RickAndMortyRepository {
    override suspend fun getCharacters(
        search: String,
        status: String,
        species: String,
        type: String,
        gender: String,
        page: Int,
    ): Character {
        return rickAndMortyApi.getCharacters(
            search, status, species, type, gender, page
        ).body() ?: Character()
    }

    override suspend fun getLocations(name: String, dimension: String, page: Int): Location {
        return rickAndMortyApi.getLocations(
            name, dimension, page
        ).body() ?: Location()
    }

    override suspend fun getEpisodes(name: String, episode: Int?, page: Int): Episode {
        return rickAndMortyApi.getEpisodes(
            name, episode, page
        ).body() ?: Episode()
    }

    override suspend fun getCharacterById(id: Int): Response<CharacterItem> {
        return rickAndMortyApi.getCharacterById(id = id)
    }

    override suspend fun getEpisodeById(id: Int): Response<EpisodeItem> {
        return rickAndMortyApi.getEpisodeById(id)
    }
}