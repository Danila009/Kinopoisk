package com.example.core_network_domain.model.IMDb.externalSite


import kotlinx.serialization.Serializable

@Serializable
data class MYmovies(
    val id: String,
    val url: String
)