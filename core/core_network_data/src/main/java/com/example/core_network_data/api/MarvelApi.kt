package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.MARVEL_CHARACTERS_URL
import com.example.core_network_data.common.ConstantsUrl.MARVEL_COMICS_URL
import com.example.core_network_data.common.ConstantsUrl.MARVEL_COMIC_CHARACTERS_URL
import com.example.core_network_domain.model.marvel.character.MarvelCharacter
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET(MARVEL_CHARACTERS_URL)
    suspend fun getCharacters(
        @Query("nameStartsWith") search:String,
        @Query("orderBy") orderBy:String = "name",
        @Query("apikey") apikey: String = "516b9065d41d0d5ea50150666658aeb8"
    ):Response<MarvelCharacter>

    @GET(MARVEL_COMICS_URL)
    suspend fun getComics(
        @Query("titleStartsWith") search: String?,
        @Query("apikey") apikey: String = "516b9065d41d0d5ea50150666658aeb8",
        @Query("ts") ts:String = "1",
        @Query("hash") hash:String = "4e2f0e14b20b46cc79f37132fc44d443",
        @Query("offset") offset:Int
    ):Response<ComicsMarvel>

    @GET("$MARVEL_COMICS_URL/{comicId}")
    suspend fun getComicById(
        @Path("comicId") comicId:Int,
        @Query("apikey") apikey: String = "516b9065d41d0d5ea50150666658aeb8",
        @Query("ts") ts:String = "1",
        @Query("hash") hash:String = "4e2f0e14b20b46cc79f37132fc44d443"
    ):Response<ComicsMarvel>

    @GET(MARVEL_COMIC_CHARACTERS_URL)
    suspend fun getComicCharacters(
        @Path("comicId") comicId:Int,
        @Query("apikey") apikey: String = "516b9065d41d0d5ea50150666658aeb8",
        @Query("ts") ts:String = "1",
        @Query("hash") hash:String = "4e2f0e14b20b46cc79f37132fc44d443",
        @Query("offset") offset:Int
    ):MarvelCharacter
}