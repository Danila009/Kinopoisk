package com.example.core_network_domain.model.marvel.character

import kotlinx.serialization.Serializable

@Serializable
data class MarvelCharacter(
    val attributionHTML: String = "",
    val attributionText: String = "",
    val code: Int = 0,
    val copyright: String = "",
    val `data`: Data = Data(),
    val etag: String = "",
    val status: String = ""
)