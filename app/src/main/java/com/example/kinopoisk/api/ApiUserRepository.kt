package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.FilmItem
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
}