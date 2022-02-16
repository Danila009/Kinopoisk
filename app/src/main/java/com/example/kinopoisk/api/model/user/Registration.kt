package com.example.kinopoisk.api.model.user

data class Registration(
    val username:String,
    val email:String,
    val password:String,
    val photo:String? = null
)