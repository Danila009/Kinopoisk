package com.example.core_network_domain.model.rickAndMorty

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val info:Info? = null,
    val results:List<CharacterItem> = listOf()
)
