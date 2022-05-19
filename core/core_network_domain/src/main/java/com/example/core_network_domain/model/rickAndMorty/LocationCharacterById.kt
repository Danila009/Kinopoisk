package com.example.core_network_domain.model.rickAndMorty

import kotlinx.serialization.Serializable

@Serializable
data class LocationCharacterById(
    val name:String,
    val url:String
)