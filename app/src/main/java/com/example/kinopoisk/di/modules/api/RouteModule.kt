package com.example.kinopoisk.di.modules.api

import com.example.core_network_data.api.RouteApi
import com.example.core_network_data.repository.RouteRepositoryImpl
import com.example.core_network_domain.repository.RouteRepository
import com.example.kinopoisk.utils.Constants.ROUTE_BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RouteModule {

    @[Provides Singleton]
    fun providerRouteRepository(
        routeApi: RouteApi
    ):RouteRepository = RouteRepositoryImpl(
        routeApi = routeApi
    )

    @[Provides Singleton ExperimentalSerializationApi]
    fun providerRouteApi(
        contentType: MediaType,
        json: Json
    ):RouteApi = Retrofit.Builder()
        .baseUrl(ROUTE_BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(RouteApi::class.java)
}