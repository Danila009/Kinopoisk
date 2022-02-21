package com.example.kinopoisk.api.repository

import com.example.kinopoisk.api.ApiUser
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.cinema.Review
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.*
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

    suspend fun putUserPhoto(
        photo: PhotoUser
    ) = apiUser.putUserPhoto(
        photo = photo
    )

    suspend fun getCinemas():Response<List<Cinema>> = apiUser.getCinemas()

    suspend fun getCinema(
        id:Int
    ):Response<Cinema> = apiUser.getCinema(
        id = id
    )

    suspend fun postCinemaReview(
        reviewCinema:Review,
        cinemaId:Int
    ) = apiUser.postCinemaReview(
        cinemaId = cinemaId,
        review = reviewCinema
    )
}