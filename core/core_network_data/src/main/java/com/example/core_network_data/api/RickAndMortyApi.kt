package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.RIAK_AND_MORTY_CHARACTER_BY_ID_URL
import com.example.core_network_data.common.ConstantsUrl.RIAK_AND_MORTY_CHARACTER_URL
import com.example.core_network_data.common.ConstantsUrl.RIAK_AND_MORTY_EPISODES_BY_ID_URL
import com.example.core_network_data.common.ConstantsUrl.RIAK_AND_MORTY_EPISODES_URL
import com.example.core_network_data.common.ConstantsUrl.RIAK_AND_MORTY_LOCATION_URL
import com.example.core_network_domain.model.rickAndMorty.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET(RIAK_AND_MORTY_CHARACTER_URL)
    suspend fun getCharacters(
        @Query("name") search:String,
        @Query("status") status:String,
        @Query("species") species:String,
        @Query("type") type:String,
        @Query("gender") gender:String,
        @Query("page") page:Int
    ):Response<Character>

    @GET(RIAK_AND_MORTY_LOCATION_URL)
    suspend fun getLocations(
        @Query("name") name:String,
        @Query("dimension") dimension:String,
        @Query("page") page:Int
    ):Response<Location>

    @GET(RIAK_AND_MORTY_EPISODES_URL)
    suspend fun getEpisodes(
        @Query("name") name:String,
        @Query("episode") episode:Int?,
        @Query("page") page:Int
    ):Response<Episode>

    @GET(RIAK_AND_MORTY_CHARACTER_BY_ID_URL)
    suspend fun getCharacterById(
        @Path("id") id:Int
    ):Response<CharacterItem>

    @GET(RIAK_AND_MORTY_EPISODES_BY_ID_URL)
    suspend fun getEpisodeById(
        @Path("id") id:Int
    ):Response<EpisodeItem>
}