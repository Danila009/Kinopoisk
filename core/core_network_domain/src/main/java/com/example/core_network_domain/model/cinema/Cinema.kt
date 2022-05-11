package com.example.core_network_domain.model.cinema

import kotlinx.serialization.Serializable

@Serializable
data class Cinema(
    val adress: String = "",
    val description: String = "",
    val has3D: Boolean = false,
    val has4DX: Boolean = false,
    val hasImax: Boolean = false,
    val icon: String = "",
    val id: Int = 0,
    val latitude: Double = 0.1,
    val longitude: Double = 0.1,
    val rating: Float = 0f,
    val title: String = "",
    val webFacebook: String = "",
    val webInstagram: String = "",
    val webVk: String = "",
    val website: String = ""
)