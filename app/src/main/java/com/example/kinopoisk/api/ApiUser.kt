package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.Authorization
import com.example.kinopoisk.api.model.user.Header
import com.example.kinopoisk.api.model.user.Registration
import com.example.kinopoisk.api.model.user.UserInfo
import com.example.kinopoisk.utils.Constants
import com.example.kinopoisk.utils.Constants.AUTHORIZATION_URL
import com.example.kinopoisk.utils.Constants.REGISTRATION_URL
import com.example.kinopoisk.utils.Constants.USER_FAVORITE_FILM_URL
import com.example.kinopoisk.utils.Constants.USER_INFO_URL
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiUser {

    @POST(AUTHORIZATION_URL)
    suspend fun postAuthorization(
        @Body authorization: Authorization
    ):Response<Header>

    @POST(REGISTRATION_URL)
    suspend fun postRegistration(
        @Body registration: Registration
    )

    @GET(USER_INFO_URL)
    suspend fun getUserInfo():Response<UserInfo>

    @POST(USER_FAVORITE_FILM_URL)
    suspend fun postFavoriteFilm(
        @Body filmItem: FilmItem
    )

    @GET(Constants.SHOP_FILM_URL)
    suspend fun getShop(
        @Query("ratingMin") ratingMin:Float?,
        @Query("ratingMax") ratingMax:Float?,
        @Query("search") search: String?,
        @Query("page") page:Int
    ):Response<List<Shop>>
}