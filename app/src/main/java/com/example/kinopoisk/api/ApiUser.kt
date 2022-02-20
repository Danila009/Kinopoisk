package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.Authorization
import com.example.kinopoisk.api.model.user.Header
import com.example.kinopoisk.api.model.user.Registration
import com.example.kinopoisk.api.model.user.UserInfo
import com.example.kinopoisk.utils.Constants
import com.example.kinopoisk.utils.Constants.AUTHORIZATION_URL
import com.example.kinopoisk.utils.Constants.CINEMA_ID_URL
import com.example.kinopoisk.utils.Constants.CINEMA_URL
import com.example.kinopoisk.utils.Constants.REGISTRATION_URL
import com.example.kinopoisk.utils.Constants.USER_FAVORITE_CHECK_FILM_URL
import com.example.kinopoisk.utils.Constants.USER_FAVORITE_FILM_ID_KINOPOISK_URL
import com.example.kinopoisk.utils.Constants.USER_FAVORITE_FILM_URL
import com.example.kinopoisk.utils.Constants.USER_INFO_URL
import com.example.kinopoisk.utils.Constants.USER_UPDATE_PASSWORD_URL
import retrofit2.Response
import retrofit2.http.*

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

    @DELETE(USER_FAVORITE_FILM_ID_KINOPOISK_URL)
    suspend fun deleteFavoriteFilm(
        @Query("idKinopoisk") kinopoiskId: Int
    )

    @PUT(USER_UPDATE_PASSWORD_URL)
    suspend fun putUserPassword(
        @Query("email") email:String,
        @Query("password") password:String
    )

    @GET(Constants.SHOP_FILM_URL)
    suspend fun getShop(
        @Query("ratingMin") ratingMin:Float?,
        @Query("ratingMax") ratingMax:Float?,
        @Query("search") search: String?,
        @Query("page") page:Int
    ):Response<List<Shop>>

    @GET(USER_FAVORITE_CHECK_FILM_URL)
    suspend fun getUserFavoriteCheck(
        @Query("KinopoiskId") kinopoiskId:Int
    ):Response<Boolean>

    @GET(CINEMA_URL)
    suspend fun getCinemas():Response<List<Cinema>>

    @GET(CINEMA_ID_URL)
    suspend fun getCinema(
        @Path("id") id:Int
    ):Response<Cinema>
}