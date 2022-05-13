package com.example.core_network_domain.model.cinema

import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.Serializable

@Serializable
data class CinemaAddReview(
    val title:String,
    val description:String,
    val rating:Float,
    @Serializable(DateTimeSerialization::class)
    val date:String
)
