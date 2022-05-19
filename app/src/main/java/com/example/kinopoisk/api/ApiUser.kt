package com.example.kinopoisk.api

import com.example.kinopoisk.api.model.shop.Shop
import com.example.kinopoisk.api.model.user.*
import com.example.kinopoisk.api.model.user.admin.filmList.AdminFilmList
import com.example.kinopoisk.common.Constants.ADMIN_FILM_LIST_URL
import com.example.kinopoisk.common.Constants.ADMIN_SHOP_ADD_FILM_ITEM_URL
import com.example.kinopoisk.common.Constants.FILM_LIST_ID_URL
import com.example.kinopoisk.common.Constants.STAFF_USER_FAVORITE_CHECK_URL
import com.example.kinopoisk.common.Constants.STAFF_USER_FAVORITE_URL
import retrofit2.Response
import retrofit2.http.*

interface ApiUser {

    @GET("/Shop")
    suspend fun getShop(
        @Query("ratingMin") ratingMin:Float?,
        @Query("ratingMax") ratingMax:Float?,
        @Query("search") search: String?,
        @Query("page") page:Int
    ):Response<List<Shop>>

    @POST(STAFF_USER_FAVORITE_URL)
    suspend fun postStaffFavorite(
        @Body staffFavorite: StaffFavorite
    )

    @GET(STAFF_USER_FAVORITE_CHECK_URL)
    suspend fun getStaffFavoriteCheck(
        @Query("StaffId") staffId:Int
    ):Response<Boolean>

    @POST(ADMIN_FILM_LIST_URL)
    suspend fun postFilmList(
        @Body filmList: AdminFilmList
    )

    @GET(FILM_LIST_ID_URL)
    suspend fun getFilmListItem(
        @Path("id") id:Int
    ):Response<AdminFilmList>

    @POST(ADMIN_SHOP_ADD_FILM_ITEM_URL)
    suspend fun postShopAddFilmItem(
        @Body shop: Shop
    )
}