package com.example.kinopoisk.di.modules.api

import com.example.core_network_data.api.IMDbApi
import com.example.core_network_data.repository.IMDbRepositoryImpl
import com.example.core_network_domain.repository.IMDbRepository
import com.example.kinopoisk.utils.Constants.IMDb_BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class IMDbApiModule {

    @[Provides Singleton]
    fun providerIMDbRepository(
        imDbApi: IMDbApi
    ):IMDbRepository = IMDbRepositoryImpl(
        imDbApi = imDbApi
    )

    @[Provides Singleton]
    fun providerIMDbApi():IMDbApi = Retrofit.Builder()
        .baseUrl(IMDb_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IMDbApi::class.java)
}