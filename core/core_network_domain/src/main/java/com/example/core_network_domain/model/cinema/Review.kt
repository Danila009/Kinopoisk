package com.example.core_network_domain.model.cinema

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val total:Int,
    val items:List<ReviewItem>
)