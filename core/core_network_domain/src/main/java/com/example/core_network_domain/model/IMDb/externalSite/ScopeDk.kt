package com.example.core_network_domain.model.IMDb.externalSite


import kotlinx.serialization.Serializable

@Serializable
data class ScopeDk(
    val id: String,
    val url: String
)