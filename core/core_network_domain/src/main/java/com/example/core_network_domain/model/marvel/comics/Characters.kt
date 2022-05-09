package com.example.core_network_domain.model.marvel.comics

data class Characters(
    val available: Int?,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: Int?
)