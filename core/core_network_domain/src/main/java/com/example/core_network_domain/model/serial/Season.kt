package com.example.core_network_domain.model.serial

import kotlinx.serialization.Serializable

@Serializable
data class Season(
    val total:Int? = null,
    val items:List<SeasonItem> = listOf()
)