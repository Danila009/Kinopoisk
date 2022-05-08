package com.example.core_network_domain.model.rickAndMorty

import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.Serializable

@Serializable
data class LocationItem(
    val id:Int,
    val name:String,
    val type:String,
    val dimension:String,
    val url:String,
    @Serializable(with = DateTimeSerialization::class)
    val created:String
)