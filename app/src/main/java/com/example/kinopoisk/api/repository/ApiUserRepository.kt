package com.example.kinopoisk.api.repository

import com.example.kinopoisk.api.ApiUser
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.Authorization
import com.example.kinopoisk.api.model.user.Header
import com.example.kinopoisk.api.model.user.Registration
import com.example.kinopoisk.api.model.user.UserInfo
import retrofit2.Response
import javax.inject.Inject

class ApiUserRepository @Inject constructor(
    private val apiUser: ApiUser
) {
    suspend fun postAuthorization(
        authorization: Authorization
    ):Response<Header> = apiUser.postAuthorization(authorization = authorization)

    suspend fun postRegistration(
        registration: Registration
    ) = apiUser.postRegistration(registration = registration)

    suspend fun getUserInfo():Response<UserInfo> = apiUser.getUserInfo()

    suspend fun postFavoriteFilm(
        filmItem: FilmItem
    ) = apiUser.postFavoriteFilm(
        filmItem = filmItem
    )

    suspend fun getShop(
        ratingMin:Float?,
        ratingMax: Float?,
        search:String?,
        page:Int = 1
    ):Response<List<Shop>> = apiUser.getShop(
        ratingMax = ratingMax,
        ratingMin = ratingMin,
        search = search,
        page = page
    )

    suspend fun getUserFavoriteCheck(
        kinopoiskId:Int
    ):Response<Boolean> = apiUser.getUserFavoriteCheck(
        kinopoiskId = kinopoiskId
    )

    suspend fun deleteFavoriteFilm(
        kinopoiskId:Int
    ) = apiUser.deleteFavoriteFilm(
        kinopoiskId = kinopoiskId
    )

    suspend fun putUserPassword(
        email:String,
        password:String
    ) = apiUser.putUserPassword(
        email = email,
        password = password
    )
}