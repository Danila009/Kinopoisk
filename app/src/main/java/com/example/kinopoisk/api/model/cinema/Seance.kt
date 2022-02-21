package com.example.kinopoisk.api.model.cinema

data class Seance(
    val has3D: Boolean,
    val has4DX: Boolean,
    val hasImax: Boolean,
    val id: Int,
    val price: Int,
    val time: String
)