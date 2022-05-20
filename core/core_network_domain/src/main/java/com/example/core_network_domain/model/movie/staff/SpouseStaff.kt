package com.example.core_network_domain.model.movie.staff

import kotlinx.serialization.Serializable

@Serializable
data class SpouseStaff(
    val children: Int?,
    val divorced: Boolean?,
    val divorcedReason: String?,
    val name: String?,
    val personId: Int?,
    val relation: String?,
    val sex: String?,
    val webUrl: String?
)