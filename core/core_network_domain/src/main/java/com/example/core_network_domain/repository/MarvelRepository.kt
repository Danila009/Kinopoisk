package com.example.core_network_domain.repository

import com.example.core_network_domain.model.marvel.character.MarvelCharacter
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import retrofit2.Response

interface MarvelRepository {

    suspend fun getCharacters(
        search:String
    ):MarvelCharacter

    suspend fun getComics(
        search: String?,
        offset:Int
    ):ComicsMarvel?

    suspend fun getComicById(
        comicId:Int
    ): Response<ComicsMarvel>
}