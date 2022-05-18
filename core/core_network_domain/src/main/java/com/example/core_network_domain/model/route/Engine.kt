package com.example.core_network_domain.model.route

import kotlinx.serialization.Serializable

@Serializable
data class Engine(
    val build_date: String,
    val graph_date: String,
    val version: String
)