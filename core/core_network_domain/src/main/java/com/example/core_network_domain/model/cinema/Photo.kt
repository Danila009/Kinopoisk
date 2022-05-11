package com.example.core_network_domain.model.cinema

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id:Int,
    val photo:String
)