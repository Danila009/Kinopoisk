package com.example.core_network_domain.model.person

data class PersonItem(
    val kinopoiskId:Int,
    val webUrl:String,
    val nameRu:String = "",
    val nameEn:String,
    val sex:String,
    val posterUrl:String
)
