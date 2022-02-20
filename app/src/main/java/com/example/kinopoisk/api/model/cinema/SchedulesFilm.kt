package com.example.kinopoisk.api.model.cinema

data class SchedulesFilm(
    val date: String,
    val id: Int,
    val premieres: List<Premiere>,
    val week: String
)