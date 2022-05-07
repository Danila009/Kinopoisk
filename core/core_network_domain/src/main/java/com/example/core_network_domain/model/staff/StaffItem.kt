package com.example.core_network_domain.model.staff

import kotlinx.serialization.Serializable

@Serializable
data class StaffItem(
    val id:Int,
    val staffId:Int,
    val nameRu:String,
    val nameEn:String,
    val description:String,
    val posterUrl:String,
    val professionText:String,
    val professionKey:String
)
