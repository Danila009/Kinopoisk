package com.example.kinopoisk.api.model.cinema

data class Schedule(
    val endDate: String,
    val id: Int,
    val startDate: String,
    val week: String
)