package com.example.kinopoisk.di.modules.api

import android.content.Context
import android.content.SharedPreferences
import com.example.core_network_data.api.*
import com.example.core_network_data.repository.*
import com.example.core_network_domain.repository.*
import com.example.kinopoisk.di.annotationName.MyKinopoiskApi
import com.example.kinopoisk.di.annotationName.UserOkHttpClient
import com.example.kinopoisk.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MyKinopoiskApiModule {

    @[Provides Singleton]
    fun providerMovieVideoRepository(
        movieVideoApi: MovieVideoApi
    ):MovieVideoRepository = MovieVideoRepositoryImpl(
        movieVideoApi = movieVideoApi
    )

    @[Provides Singleton]
    fun providerMovieVideoApi(
        @MyKinopoiskApi retrofit: Retrofit
    ):MovieVideoApi = retrofit.create(MovieVideoApi::class.java)

    @[Provides Singleton]
    fun providerUserContentRepository(
        userContentApi: UserContentApi
    ):UserContentRepository = UserContentRepositoryImpl(
        userContentApi = userContentApi
    )

    @[Provides Singleton]
    fun providerUserContentApi(
        @MyKinopoiskApi retrofit: Retrofit
    ):UserContentApi = retrofit.create(UserContentApi::class.java)

    @[Provides Singleton]
    fun providerHistoryRepository(
        historyApi: HistoryApi
    ):HistoryRepository = HistoryRepositoryImpl(
        historyApi = historyApi
    )

    @[Provides Singleton]
    fun providerHistoryApi(
        @MyKinopoiskApi retrofit: Retrofit
    ):HistoryApi = retrofit.create(HistoryApi::class.java)

    @[Provides Singleton ExperimentalSerializationApi]
    fun providerPlaylistRepository(
        playlistApi: PlaylistApi
    ):PlaylistRepository = PlaylistRepositoryImpl(
        playlistApi = playlistApi
    )

    @[Provides Singleton ExperimentalSerializationApi]
    fun providerPlaylistApi(
        @MyKinopoiskApi retrofit: Retrofit
    ):PlaylistApi = retrofit.create(PlaylistApi::class.java)

    @[Provides Singleton]
    fun providerCinemaRepository(
        cinemaApi: CinemaApi
    ):CinemaRepository = CinemaRepositoryImpl(
        cinemaApi = cinemaApi
    )

    @[Provides Singleton]
    fun providerCinemaApi(
        @MyKinopoiskApi retrofit: Retrofit
    ):CinemaApi = retrofit.create(CinemaApi::class.java)

    @[Provides Singleton]
    fun providerShopRepository(
        shopApi: ShopApi
    ):ShopRepository = ShopRepositoryImpl(
        shopApi = shopApi
    )

    @[Provides Singleton]
    fun providerShopApi(
        @MyKinopoiskApi retrofit: Retrofit
    ):ShopApi = retrofit.create(ShopApi::class.java)

    @[Provides Singleton]
    fun providerUserRepository(
        userApi: UserApi
    ): UserRepository = UserRepositoryImpl(userApi)

    @[Provides Singleton]
    fun providerUserApi(
        retrofit: Retrofit
    ):UserApi = retrofit.create(UserApi::class.java)

    @[Provides Singleton MyKinopoiskApi ExperimentalSerializationApi]
    fun providerMayKinopoiskApiRetrofit(
        @UserOkHttpClient okHttpClient: OkHttpClient,
        contentType: MediaType,
        json: Json
    ): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_USER_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(okHttpClient)
        .build()

    @[Singleton Provides]
    fun providerContentType(): MediaType = "application/json".toMediaTypeOrNull()!!

    @[Singleton Provides]
    fun providerJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @[Provides Singleton UserOkHttpClient]
    fun providerUserOkhttp(
        sharedPreferences: SharedPreferences
    ):OkHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader(
                    name = "Authorization",
                    value = "Bearer ${sharedPreferences.getString(Constants.TOKEN_SHARED, "")}"
                )
                .build()
            it.proceed(request)
        }
        .build()

    @[Provides Singleton]
    fun providerUserTokenPreferences(
        context: Context
    ): SharedPreferences = context.getSharedPreferences(Constants.TOKEN_SHARED, Context.MODE_PRIVATE)
}