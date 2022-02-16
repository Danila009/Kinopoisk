package com.example.kinopoisk.di

import com.example.kinopoisk.api.ApiKinopoisk
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.kinopoisk.api.ApiUser
import com.example.kinopoisk.di.annotationName.KinopoiskOkHttpClient
import com.example.kinopoisk.utils.Constants
import com.example.kinopoisk.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providerApi(
        apiKinopoisk: ApiKinopoisk,
        apiUser: ApiUser
    ) = ApiRepository(
        apiKinopoisk = apiKinopoisk
    )

    @Provides
    @Singleton
    fun providerRetrofit(
        @KinopoiskOkHttpClient okHttpClient: OkHttpClient
    ):ApiKinopoisk = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiKinopoisk::class.java)

    @Provides
    @Singleton
    @KinopoiskOkHttpClient
    fun providerOkhttp():OkHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .header("X-API-KEY",Constants.TOKEN_KEY)
                .build()
            it.proceed(request)
        }
        .build()
}