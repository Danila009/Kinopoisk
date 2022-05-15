package com.example.core_network_domain.model.cinema

import com.example.core_network_domain.model.user.User
import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class ReviewItem(
    val id:Int,
    val cinemaId:Int,
    val title:String,
    val description:String,
    val rating:Float,
    @Serializable(with = DateTimeSerialization::class)
    val date:String,
    val user:User
)