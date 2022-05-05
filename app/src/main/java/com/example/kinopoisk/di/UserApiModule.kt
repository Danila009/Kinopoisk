package com.example.kinopoisk.di

import com.example.kinopoisk.api.repository.ApiUserRepository
import com.example.kinopoisk.api.ApiUser
import com.example.kinopoisk.di.annotationName.UserOkHttpClient
import com.example.kinopoisk.utils.Constants.BASE_USER_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class UserApiModule {

    @Provides
    @Singleton
    fun providerUserApiRepository(
        apiUser: ApiUser,
    ) = ApiUserRepository(
        apiUser = apiUser
    )

    @[Provides Singleton]
    fun providerApiUser(
        retrofit: Retrofit
    ):ApiUser = retrofit.create(ApiUser::class.java)

    @Provides
    @Singleton
    fun providerUserRetrofit(
        @UserOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_USER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}
