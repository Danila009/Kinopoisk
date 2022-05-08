package com.example.core_network_domain.model.rickAndMorty

import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val info: Info? = null,
    val results:List<EpisodeItem> = listOf()
)