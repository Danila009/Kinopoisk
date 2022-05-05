package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.SHOP_URL
import com.example.core_network_domain.model.shop.Shop
import retrofit2.Response
import retrofit2.http.GET

interface ShopApi {

    @GET(SHOP_URL)
    suspend fun getShop(): Response<Shop>
}