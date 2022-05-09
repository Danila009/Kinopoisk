package com.example.kinopoisk.di.modules.api

import com.example.core_network_data.api.MarvelApi
import com.example.core_network_data.repository.MarvelRepositoryImpl
import com.example.core_network_domain.repository.MarvelRepository
import com.example.kinopoisk.utils.Constants.MARVEL_BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MarvelApiModule {

    @[Provides Singleton]
    fun providerMarvelRepository(
        marvelApi: MarvelApi
    ):MarvelRepository = MarvelRepositoryImpl(
        marvelApi = marvelApi
    )

    @[Provides Singleton]
    fun providerMarvelApi():MarvelApi = Retrofit.Builder()
        .baseUrl(MARVEL_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MarvelApi::class.java)
}