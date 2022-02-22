package com.example.kinopoisk.api.model.user

data class FavoriteStaff(
    val id:Int,
    val staffId:Int,
    val nameRu:String,
    val nameEn:String,
    val description:String,
    val posterUrl:String,
    val professionText:String,
    val professionKey:String
)