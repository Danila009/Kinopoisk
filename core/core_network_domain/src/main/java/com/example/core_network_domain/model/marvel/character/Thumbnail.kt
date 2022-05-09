package com.example.core_network_domain.model.marvel.character

import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    val extension: String,
    val path: String
)