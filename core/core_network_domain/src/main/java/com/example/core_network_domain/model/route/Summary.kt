package com.example.core_network_domain.model.route

import kotlinx.serialization.Serializable

@Serializable
data class Summary(
    val distance: Double,
    val duration: Double
)