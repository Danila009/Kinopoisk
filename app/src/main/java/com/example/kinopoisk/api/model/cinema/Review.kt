package com.example.kinopoisk.api.model.cinema

data class Review(
    val cinemaId: Int,
    val date: String,
    val description: String,
    val id: Int,
    val rating: Float,
    val title: String,
    val userId: Int,
    val userName: String
)