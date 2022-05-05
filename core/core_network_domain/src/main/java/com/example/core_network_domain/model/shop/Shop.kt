package com.example.core_network_domain.model.shop

import kotlinx.serialization.Serializable

@Serializable
data class Shop(
    val total:Int = 0,
    val catecory:List<ShopCategory> = listOf()
)