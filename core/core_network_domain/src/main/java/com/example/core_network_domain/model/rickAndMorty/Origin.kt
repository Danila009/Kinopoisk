package com.example.core_network_domain.model.rickAndMorty

import kotlinx.serialization.Serializable

@Serializable
data class Origin(
    val name:String,
    val url:String
)