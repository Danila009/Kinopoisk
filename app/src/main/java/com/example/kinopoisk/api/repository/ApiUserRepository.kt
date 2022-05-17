package com.example.kinopoisk.api.repository

import com.example.kinopoisk.api.ApiUser
import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.*
import com.example.kinopoisk.api.model.user.admin.filmList.AdminFilmList
import retrofit2.Response
import javax.inject.Inject

class ApiUserRepository @Inject constructor(
    private val apiUser: ApiUser
) {
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

    suspend fun postStaffFavorite(staffFavorite: StaffFavorite) = apiUser.postStaffFavorite(staffFavorite = staffFavorite)

    suspend fun getStaffFavoriteCheck(staffId:Int):Response<Boolean> = apiUser.getStaffFavoriteCheck(staffId = staffId)

    suspend fun postFilmList(filmList: AdminFilmList) = apiUser.postFilmList(filmList = filmList)

    suspend fun getFilmListItem(id:Int):Response<AdminFilmList> = apiUser.getFilmListItem(id = id)

    suspend fun postShopAddFilmItem(shop: Shop) = apiUser.postShopAddFilmItem(shop = shop)
}