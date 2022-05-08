package com.example.core_utils.navigation.filmNavGraph.filmInfoNavGraph

sealed class FilmScreenRoute(val route:String){
    object FilmInfo: FilmScreenRoute("film_info_screen?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "film_info_screen?filmId=$filmId"
    }
    object SerialInfoSeason: FilmScreenRoute("serial_info_season?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "serial_info_season?filmId=$filmId"
    }
    object WebScreen: FilmScreenRoute("web_screen?webUrl={webUrl}"
    ){
        fun base(
            webUrl:String,
        ):String = "web_screen?webUrl=$webUrl"
    }
}
