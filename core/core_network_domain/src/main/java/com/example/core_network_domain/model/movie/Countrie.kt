package com.example.core_network_domain.model.movie

import kotlinx.serialization.Serializable

@Serializable
data class Countrie(
    val id:Int? = null,
    val country:String
)