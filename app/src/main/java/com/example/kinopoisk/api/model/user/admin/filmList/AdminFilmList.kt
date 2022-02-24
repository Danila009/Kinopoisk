package com.example.kinopoisk.api.model.user.admin.filmList


import com.example.kinopoisk.api.model.FilmItem

data class AdminFilmList(
    val date: String,
    val id: Int? = null,
    val movies: List<FilmItem>,
    val title: String = ""
)