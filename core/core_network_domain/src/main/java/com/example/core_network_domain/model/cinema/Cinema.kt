package com.example.core_network_domain.model.cinema

import kotlinx.serialization.Serializable

@Serializable
data class Cinema(
    val adress: String?,
    val description: String?,
    val has3D: Boolean?,
    val has4DX: Boolean?,
    val hasImax: Boolean?,
    val icon: String?,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val rating: Int?,
    val title: String?,
    val webFacebook: String?,
    val webInstagram: String?,
    val webVk: String?,
    val website: String?
)