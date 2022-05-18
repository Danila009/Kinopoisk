package com.example.core_network_domain.model.route

import kotlinx.serialization.Serializable

@Serializable
data class Query(
    val coordinates: List<List<Double>>,
    val format: String,
    val profile: String
)