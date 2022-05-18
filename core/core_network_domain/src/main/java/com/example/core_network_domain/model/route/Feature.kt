package com.example.core_network_domain.model.route

import kotlinx.serialization.Serializable

@Serializable
data class Feature(
    val bbox: List<Double>,
    val geometry: Geometry,
    val properties: Properties,
    val type: String
)