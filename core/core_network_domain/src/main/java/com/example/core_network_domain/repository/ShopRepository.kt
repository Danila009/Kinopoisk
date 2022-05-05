package com.example.core_network_domain.repository

import com.example.core_network_domain.model.shop.Shop

interface ShopRepository {

    suspend fun getShop():Shop
}