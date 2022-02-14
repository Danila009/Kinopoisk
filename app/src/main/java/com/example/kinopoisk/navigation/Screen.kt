package com.example.kinopoisk.navigation

const val ROUTE = "route"
const val MAIN_ROUTE = "main_route"
const val FILM_INFO_ROUTE = "film_info_route"

const val RATING_FROM_ARGUMENT = "ratingFrom"
const val RATING_TO_ARGUMENT = "ratingTo"
const val YEAR_FROM_ARGUMENT = "yearFrom"
const val YEAR_TO_ARGUMENT = "yearTo"
const val FILM_ID_ARGUMENT = "filmId"

sealed class Screen(val route:String) {
    object Main:Screen("main_screen")
    object Sorting:Screen("sorting_screen")
    object ResultSorting:Screen("result_sorting_screen?" +
            "ratingFrom={ratingFrom}" +
            "&ratingTo={ratingTo}" +
            "&yearFrom={yearFrom}" +
            "&yearTo={yearTo}"
    ){
        fun base(
            ratingFrom:String = "0",
            ratingTo:String = "10",
            yearFrom:String = "1000",
            yearTo:String = "3000"
        ):String = "result_sorting_screen?" +
                "ratingFrom=$ratingFrom" +
                "&ratingTo=$ratingTo" +
                "&yearFrom=$yearFrom" +
                "&yearTo=$yearTo"
    }
    object FilmInfo:Screen("film_info_screen?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "film_info_screen?filmId=$filmId"
    }
    object SerialInfoSeason:Screen("serial_info_season?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "serial_info_season?filmId=$filmId"
    }
}