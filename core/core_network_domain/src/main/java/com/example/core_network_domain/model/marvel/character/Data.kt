package com.example.core_network_domain.model.marvel.character

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val count: Int = 0,
    val limit: Int = 0,
    val offset: Int = 0,
    val results: List<Result> = emptyList(),
    val total: Int = 0
)