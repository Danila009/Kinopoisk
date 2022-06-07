package com.example.core_network_domain.model.movie.award

import kotlinx.serialization.Serializable

@Serializable
data class Award(
    val total:Int,
    val items:List<AwardItem>
)