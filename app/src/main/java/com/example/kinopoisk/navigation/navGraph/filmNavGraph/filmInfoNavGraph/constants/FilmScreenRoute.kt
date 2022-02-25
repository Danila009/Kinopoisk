package com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.constants

sealed class FilmScreenRoute(val route:String){
    object FilmInfo: FilmScreenRoute("film_info_screen?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "film_info_screen?filmId=$filmId"
    }
    object SerialInfoSeason:FilmScreenRoute("serial_info_season?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "serial_info_season?filmId=$filmId"
    }
    object WebScreen: FilmScreenRoute("web_screen?filmId={filmId}&keyWebScreen={keyWebScreen}&webUrl={webUrl}&cinemaId={cinemaId}"){
        fun base(
            filmId:String? = null,
            keyString: String,
            webUrl:String,
            cinemaId:String? = null
        ):String = "web_screen?filmId=$filmId&keyWebScreen=$keyString&webUrl=$webUrl&cinemaId=$cinemaId"
    }
}
