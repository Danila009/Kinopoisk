package com.example.core_network_domain.model.marvel.character

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val description: String,
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val thumbnail: Thumbnail
)