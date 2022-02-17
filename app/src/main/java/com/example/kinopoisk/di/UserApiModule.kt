package com.example.kinopoisk.di

import android.content.Context
import android.content.SharedPreferences
import com.example.kinopoisk.api.repository.ApiUserRepository
import com.example.kinopoisk.api.ApiUser
import com.example.kinopoisk.di.annotationName.UserOkHttpClient
import com.example.kinopoisk.utils.Constants.BASE_USER_URL
import com.example.kinopoisk.utils.Constants.TOKEN_SHARED
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserApiModule {

    @Provides
    @Singleton
    fun providerUserApi(
        apiUser: ApiUser,
    ) = ApiUserRepository(
        apiUser = apiUser
    )

    @Provides
    @Singleton
    fun providerUserRetrofit(
        @UserOkHttpClient okHttpClient: OkHttpClient
    ): ApiUser = Retrofit.Builder()
        .baseUrl(BASE_USER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ApiUser::class.java)

    @Provides
    @Singleton
    @UserOkHttpClient
    fun providerUserOkhttp(
        sharedPreferences: SharedPreferences
    ):OkHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader(
                    name = "Authorization",
                    value = "Bearer ${sharedPreferences.getString(TOKEN_SHARED, "")}"
                )
                .build()
            it.proceed(request)
        }
        .build()

    @Provides
    @Singleton
    fun providerUserTokenPreferences(
        @ApplicationContext context: Context
    ):SharedPreferences = context.getSharedPreferences(TOKEN_SHARED, Context.MODE_PRIVATE)
}
