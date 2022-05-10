package com.example.core_utils.state

import com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph.FilmScreenRoute

enum class CharacterState(
    val image:String,
    val nav:String
) {
    RICK_AND_MORTY(
        image = "https://wallpapersprinted.com/download/2/rick_and_morty_logo-wallpaper-2880x1800.jpg",
        // Riak and morty id  kinopoisk 685246
        nav = FilmScreenRoute.SerialInfoSeason.base(filmId = "685246",characters = true)
    )
}