package com.example.kinopoisk.api.model.staff

data class Staff(
    val staffId:Int = 0,
    val nameRu:String = "",
    val nameEn:String = "",
    val description:String = "",
    val posterUrl:String? = null,
    val professionText:String = "",
    val professionKey:String
)