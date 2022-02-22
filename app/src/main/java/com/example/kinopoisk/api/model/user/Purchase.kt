package com.example.kinopoisk.api.model.user

import com.example.kinopoisk.api.model.shop.Shop

data class Purchase(
    val id:Int,
    val date:String,
    val shop:Shop
)
