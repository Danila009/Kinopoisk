package com.example.kinopoisk.api.model.cinema

data class Cinema(
    val adress: String = "",
    val description: String = "",
    val has3D: Boolean = false,
    val has4DX: Boolean = false,
    val hasImax: Boolean = false,
    val icon: String = "",
    val id: Int = 0,
    val mapOne: Double = 0.0,
    val mapTwo: Double = 0.0,
    val phoneItems: List<PhoneItem> = listOf(),
    val photoItems: List<PhotoItem> = listOf(),
    val rating: Float = 0f,
    val reviews: List<Review> = listOf(),
    val schedules: List<Schedule> = listOf(),
    val schedulesFilms: List<SchedulesFilm> = listOf(),
    val title: String = "",
    val webFacebook: String = "",
    val webInstagram: String = "",
    val webVk: String = "",
    val website: String = ""
)