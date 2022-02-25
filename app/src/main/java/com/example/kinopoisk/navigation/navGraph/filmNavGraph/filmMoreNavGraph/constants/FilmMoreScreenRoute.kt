package com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmMoreNavGraph.constants

sealed class FilmMoreScreenRoute(val route:String){
    object ImageFilmMore: FilmMoreScreenRoute("image_film_more_screen?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "image_film_more_screen?filmId=$filmId"
    }
    object ReviewFilmMore:FilmMoreScreenRoute("review_film_more_screen?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "review_film_more_screen?filmId=$filmId"
    }
}
