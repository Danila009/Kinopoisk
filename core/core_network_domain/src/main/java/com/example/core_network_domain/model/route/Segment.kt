package com.example.core_network_domain.model.route

import kotlinx.serialization.Serializable


@Serializable
data class Segment(
    val distance: Double,
    val duration: Double,
    val steps: List<Step>
)