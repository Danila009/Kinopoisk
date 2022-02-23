package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.cinema.Review
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.*
import com.example.kinopoisk.api.model.user.Header
import com.example.kinopoisk.api.model.user.history.History
import com.example.kinopoisk.utils.Constants
import com.example.kinopoisk.utils.Constants.AUTHORIZATION_URL
import com.example.kinopoisk.utils.Constants.CINEMA_ID_URL
import com.example.kinopoisk.utils.Constants.CINEMA_URL
import com.example.kinopoisk.utils.Constants.HISTORY_ALL_URL
import com.example.kinopoisk.utils.Constants.HISTORY_USER_URL
import com.example.kinopoisk.utils.Constants.PURCHASE_USER_ID_KINOPOISK_URK
import com.example.kinopoisk.utils.Constants.PURCHASE_USER_URK
import com.example.kinopoisk.utils.Constants.REGISTRATION_URL
import com.example.kinopoisk.utils.Constants.SHOP_CHECK_URL
import com.example.kinopoisk.utils.Constants.SHOP_FILM_ID_KINOPOISK_URL
import com.example.kinopoisk.utils.Constants.STAFF_USER_FAVORITE_CHECK_URL
import com.example.kinopoisk.utils.Constants.STAFF_USER_FAVORITE_URL
import com.example.kinopoisk.utils.Constants.USER_CINEMA_REVIEW_CINEMA_ID_URL
import com.example.kinopoisk.utils.Constants.USER_FAVORITE_CHECK_FILM_URL
import com.example.kinopoisk.utils.Constants.USER_FAVORITE_FILM_ID_KINOPOISK_URL
import com.example.kinopoisk.utils.Constants.USER_FAVORITE_FILM_URL
import com.example.kinopoisk.utils.Constants.USER_INFO_URL
import com.example.kinopoisk.utils.Constants.USER_UPDATE_PASSWORD_URL
import com.example.kinopoisk.utils.Constants.USER_UPDATE_PHOTO_URL
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

    @PUT(USER_UPDATE_PHOTO_URL)
    suspend fun putUserPhoto(
        @Body photo:PhotoUser
    )

    @GET(Constants.SHOP_FILM_URL)
    suspend fun getShop(
        @Query("ratingMin") ratingMin:Float?,
        @Query("ratingMax") ratingMax:Float?,
        @Query("search") search: String?,
        @Query("page") page:Int
    ):Response<List<Shop>>

    @GET(SHOP_CHECK_URL)
    suspend fun getShopCheck(
        @Query("kinopoiskId") kinopoiskId:Int
    ):Response<Boolean>

    @GET(SHOP_FILM_ID_KINOPOISK_URL)
    suspend fun getShopId(
        @Path("idKinopoisk") idKinopoisk:Int
    ):Response<Shop>

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

    @POST(USER_CINEMA_REVIEW_CINEMA_ID_URL)
    suspend fun postCinemaReview(
        @Path("cinemaId") cinemaId:Int,
        @Body review: Review
    )

    @GET(HISTORY_USER_URL)
    suspend fun getHistory():Response<List<History>>

    @POST(HISTORY_USER_URL)
    suspend fun postHistory(
        @Body history: History
    )

    @DELETE(HISTORY_ALL_URL)
    suspend fun deleteHistoryAll()

    @GET(PURCHASE_USER_URK)
    suspend fun getPurchase():Response<List<Purchase>>

    @POST(PURCHASE_USER_URK)
    suspend fun postPurchase(
        @Body purchase: Purchase
    )

    @POST(PURCHASE_USER_ID_KINOPOISK_URK)
    suspend fun getPurchase(
        @Path("idKinopoisk") idKinopoisk:Int
    ):Response<Boolean>

    @POST(STAFF_USER_FAVORITE_URL)
    suspend fun postStaffFavorite(
        @Body staffFavorite: StaffFavorite
    )

    @GET(STAFF_USER_FAVORITE_CHECK_URL)
    suspend fun getStaffFavoriteCheck(
        @Query("StaffId") staffId:Int
    ):Response<Boolean>
}