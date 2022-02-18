package com.example.kinopoisk.api.model.shop

data class Shop(
    val id:Int? = null,
    val kinopoiskId:Int = 0,
    val nameRu:String = "",
    val ratingKinopoisk:Float = 0f,
    val posterUrlPreview:String = "",
    val price:Int = 0
)