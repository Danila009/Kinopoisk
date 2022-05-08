package com.example.core_network_domain.model.serial

import kotlinx.serialization.Serializable

@Serializable
data class SeasonItem(
    val number:Int,
    val episodes:List<Episode>
)