package com.example.core_network_domain.model.movie

data class SequelAndPrequel(
    val filmId:Int?,
    val nameRu:String?,
    val nameEn:String?,
    val nameOriginal:String?,
    val posterUrl:String?,
    val posterUrlPreview:String?,
    val relationType:String?
)
