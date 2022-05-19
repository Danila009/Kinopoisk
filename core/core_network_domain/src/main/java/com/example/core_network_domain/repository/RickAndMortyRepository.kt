package com.example.core_network_domain.repository

import com.example.core_network_domain.model.rickAndMorty.*
import retrofit2.Response

interface RickAndMortyRepository {

    suspend fun getCharacters(
        search:String,
        status:String,
        species:String,
        type:String,
        gender:String,
        page:Int
    ):Character

    suspend fun getLocations(
       name:String,
       dimension:String,
       page:Int
    ):Location

    suspend fun getEpisodes(
        name:String,
        episode:Int?,
        page:Int
    ):Episode

    suspend fun getCharacterById(
        id:Int
    ):Response<CharacterItem>

    suspend fun getEpisodeById(
        id: Int
    ):Response<EpisodeItem>
}