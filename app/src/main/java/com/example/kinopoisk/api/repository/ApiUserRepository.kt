package com.example.kinopoisk.api.repository

import com.example.kinopoisk.api.ApiUser
import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.cinema.Cinema
import com.example.kinopoisk.api.model.cinema.Review
import com.example.kinopoisk.api.model.series.Serial
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.*
import com.example.kinopoisk.api.model.user.admin.filmList.AdminFilmList
import com.example.kinopoisk.api.model.user.history.History
import retrofit2.Response
import javax.inject.Inject

class ApiUserRepository @Inject constructor(
    private val apiUser: ApiUser
) {

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

    suspend fun getShopCheck(idKinopoisk:Int):Response<Boolean> = apiUser.getShopCheck(kinopoiskId = idKinopoisk)

    suspend fun getShopId(idKinopoisk: Int):Response<Shop> = apiUser.getShopId(idKinopoisk = idKinopoisk)

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

    suspend fun getHistory():Response<List<History>> = apiUser.getHistory()

    suspend fun deleteHistoryAll() = apiUser.deleteHistoryAll()

    suspend fun getPurchase():Response<List<Purchase>> = apiUser.getPurchase()

    suspend fun postPurchase(purchase: Purchase) = apiUser.postPurchase(purchase)

    suspend fun getPurchase(idKinopoisk: Int):Response<Boolean> = apiUser.getPurchase(idKinopoisk = idKinopoisk)

    suspend fun postHistory(history: History) = apiUser.postHistory(history = history)

    suspend fun postStaffFavorite(staffFavorite: StaffFavorite) = apiUser.postStaffFavorite(staffFavorite = staffFavorite)

    suspend fun getStaffFavoriteCheck(staffId:Int):Response<Boolean> = apiUser.getStaffFavoriteCheck(staffId = staffId)

    suspend fun postFilmList(filmList: AdminFilmList) = apiUser.postFilmList(filmList = filmList)

    suspend fun getFilmList():Response<List<AdminFilmList>> = apiUser.getFilmList()

    suspend fun getFilmListItem(id:Int):Response<AdminFilmList> = apiUser.getFilmListItem(id = id)

    suspend fun postShopAddFilmItem(shop: Shop) = apiUser.postShopAddFilmItem(shop = shop)

    suspend fun getSeriesCheck(
        kinopoiskId:Int,
        season:Int,
        series:Int
    ):Response<Boolean> = apiUser.getSerialCheck(
        kinopoiskId = kinopoiskId,
        season = season,
        series = series
    )

    suspend fun postSeries(serial: Serial) = apiUser.postSerial(serial = serial)

    suspend fun putSeries(
        viewed:Boolean,
        kinopoiskId:Int,
        season:Int,
        series:Int
    ) = apiUser.putSerial(
        viewed = viewed,
        kinopoiskId = kinopoiskId,
        series = series,
        season = season
    )
}