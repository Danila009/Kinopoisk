package com.example.core_network_domain.model.user

data class Registration(
    val id:Int = 0,
    val username:String,
    val email:String,
    val password:String,
    val photo:String?
)