package com.example.core_network_domain.model.route

import kotlinx.serialization.Serializable

@Serializable
data class Properties(
    val segments: List<Segment>,
    val summary: Summary,
    val way_points: List<Int>
)