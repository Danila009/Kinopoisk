package com.example.core_network_domain.model.user

data class AuthorizationHeader(
    val access_token:String = "",
    val username:String = "",
    val role:String = ""
)