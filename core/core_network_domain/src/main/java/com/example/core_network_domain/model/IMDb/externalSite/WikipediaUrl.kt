package com.example.core_network_domain.model.IMDb.externalSite


import kotlinx.serialization.Serializable

@Serializable
data class WikipediaUrl(
    val language: String,
    val title: String,
    val url: String
)