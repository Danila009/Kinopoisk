package com.example.core_network_domain.model.route

import kotlinx.serialization.Serializable

@Serializable
data class Step(
    val distance: Double,
    val duration: Double,
    val instruction: String,
    val name: String,
    val type: Int,
    val way_points: List<Int>
)