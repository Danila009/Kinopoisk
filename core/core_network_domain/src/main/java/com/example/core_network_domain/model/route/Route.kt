package com.example.core_network_domain.model.route

import com.example.core_network_domain.model.route.Feature
import com.example.core_network_domain.model.route.Metadata
import kotlinx.serialization.Serializable

@Serializable
data class Route(
    val bbox: List<Double>,
    val features: List<Feature>,
    val metadata: Metadata,
    val type: String
)