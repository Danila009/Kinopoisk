package com.example.core_network_domain.model.route

import kotlinx.serialization.Serializable

@Serializable
data class Metadata(
    val attribution: String,
    val engine: Engine,
    val query: Query,
    val service: String,
    val timestamp: Long
)