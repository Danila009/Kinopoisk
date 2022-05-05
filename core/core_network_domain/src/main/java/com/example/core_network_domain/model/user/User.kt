package com.example.core_network_domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val balance: Int?,
    val email: String?,
    val id: Int?,
    val password: String?,
    val photo: String?,
    val role: String?,
    val username: String?
)