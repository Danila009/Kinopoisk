package com.example.kinopoisk.di.modules.api

import com.example.core_network_data.api.RickAndMortyApi
import com.example.core_network_data.repository.RickAndMortyRepositoryImpl
import com.example.core_network_domain.repository.RickAndMortyRepository
import com.example.kinopoisk.utils.Constants.RICK_AND_MORTY_BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RickAndMortyApiModule {

    @[Singleton Provides]
    fun providerRickAndMortyRepository(
        rickAndMortyApi: RickAndMortyApi
    ):RickAndMortyRepository = RickAndMortyRepositoryImpl(
        rickAndMortyApi = rickAndMortyApi
    )

    @[Singleton Provides ExperimentalSerializationApi]
    fun providerRickAndMortyApi(
        contentType: MediaType,
        json: Json
    ): RickAndMortyApi = Retrofit.Builder()
        .baseUrl(RICK_AND_MORTY_BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(RickAndMortyApi::class.java)
}