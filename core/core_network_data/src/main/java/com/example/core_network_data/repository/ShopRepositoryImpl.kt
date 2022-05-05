package com.example.core_network_data.repository

import com.example.core_network_data.api.ShopApi
import com.example.core_network_domain.model.shop.Shop
import com.example.core_network_domain.repository.ShopRepository
import javax.inject.Inject

class ShopRepositoryImpl @Inject constructor(
    private val shopApi: ShopApi
):ShopRepository {
    override suspend fun getShop(): Shop {
        return shopApi.getShop().body()!!
    }
}