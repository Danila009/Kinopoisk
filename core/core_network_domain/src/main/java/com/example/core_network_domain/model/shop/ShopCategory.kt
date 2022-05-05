package com.example.core_network_domain.model.shop

import com.example.core_network_domain.model.shop.enum.Category
import kotlinx.serialization.Serializable

@Serializable
data class ShopCategory(
    val id:Int,
    val title:String,
    val imageUrl:String,
    val category: Category,
    val total:Int
)