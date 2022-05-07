package com.example.kinopoisk.di

import com.example.core_network_data.api.MovieApi
import com.example.core_network_data.api.PersonApi
import com.example.core_network_data.repository.MovieRepositoryImpl
import com.example.core_network_data.repository.PersonRepositoryImpl
import com.example.core_network_domain.repository.MovieRepository
import com.example.core_network_domain.repository.PersonRepository
import com.example.kinopoisk.api.ApiKinopoisk
import com.example.kinopoisk.api.repository.ApiRepository
import com.example.kinopoisk.di.annotationName.KinopoiskApi
import com.example.kinopoisk.di.annotationName.KinopoiskOkHttpClient
import com.example.kinopoisk.utils.Constants
import com.example.kinopoisk.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @[Provides Singleton]
    fun providerPersonRepository(
        personApi: PersonApi
    ):PersonRepository = PersonRepositoryImpl(
        personApi = personApi
    )

    @[Provides Singleton]
    fun providerPersonApi(
        @KinopoiskApi retrofit: Retrofit
    ):PersonApi = retrofit.create(PersonApi::class.java)

    @[Provides Singleton]
    fun providerMovieRepository(
        movieApi: MovieApi
    ): MovieRepository = MovieRepositoryImpl(
        movieApi = movieApi
    )

    @[Provides Singleton]
    fun providerMovieApi(
        @KinopoiskApi retrofit: Retrofit
    ): MovieApi = retrofit.create(MovieApi::class.java)
    
    @Provides
    @Singleton
    fun providerApi(
        apiKinopoisk: ApiKinopoisk
    ) = ApiRepository(
        apiKinopoisk = apiKinopoisk
    )

    @[Provides Singleton]
    fun providerApiKinopoisk(
        @KinopoiskApi retrofit: Retrofit
    ):ApiKinopoisk = retrofit
        .create(ApiKinopoisk::class.java)

    @[Provides Singleton KinopoiskApi]
    fun providerRetrofit(
        @KinopoiskOkHttpClient okHttpClient: OkHttpClient
    ):Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

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