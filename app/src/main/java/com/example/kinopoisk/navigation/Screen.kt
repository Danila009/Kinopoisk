package com.example.kinopoisk.navigation

const val ROUTE = "route"
const val MAIN_ROUTE = "main_route"

const val RATING_FROM_ARGUMENT = "ratingFrom_arguments"
const val RATING_TO_ARGUMENT = "ratingTo_arguments"

sealed class Screen(val route:String) {
    object Main:Screen("main_screen")
    object Sorting:Screen("sorting_screen")
    object ResultSorting:Screen("result_sorting_screen?ratingFrom_arguments={ratingFrom_arguments}&ratingTo_arguments={ratingTo_arguments}"){
        fun base(
            ratingFrom:String,
            ratingTo:String
        ):String = "result_sorting_screen?ratingFrom_arguments=$ratingFrom&ratingTo_arguments=$ratingTo"
    }
}