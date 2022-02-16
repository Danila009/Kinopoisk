package com.example.kinopoisk.di

import com.example.kinopoisk.api.ApiKinopoisk
import com.example.kinopoisk.api.ApiRepository
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
    ) = ApiRepository(
        apiKinopoisk = apiKinopoisk
    )

    @Provides
    @Singleton
    fun providerRetrofit(
        okHttpClient: OkHttpClient
    ):ApiKinopoisk = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiKinopoisk::class.java)

    @Provides
    @Singleton
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