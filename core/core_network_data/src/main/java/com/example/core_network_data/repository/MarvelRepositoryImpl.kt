package com.example.core_network_data.repository

import com.example.core_network_data.api.MarvelApi
import com.example.core_network_domain.model.marvel.character.MarvelCharacter
import com.example.core_network_domain.model.marvel.comics.ComicsMarvel
import com.example.core_network_domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val marvelApi: MarvelApi
): MarvelRepository {
    override suspend fun getCharacters(search: String): MarvelCharacter {
        return marvelApi.getCharacters(search = search).body() ?: MarvelCharacter()
    }

    override suspend fun getComics(search: String?, offset:Int): ComicsMarvel? {
        return marvelApi.getComics(search = search, offset = offset).body()
    }
}