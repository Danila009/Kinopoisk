package com.example.core_network_domain.model.user

import kotlinx.serialization.Serializable

@Serializable
data class Registration(
    val id:Int? = 0,
    val username:String,
    val email:String,
    val password:String,
    val photo:String?
)