package com.example.core_network_domain.model.rickAndMorty

import kotlinx.serialization.Serializable

@Serializable
data class Info(
    val count:Int,
    val pages:Int,
    val next:String
)
