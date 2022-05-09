package com.example.core_utils.navigation.comicsNavGraph

const val COMICS_ROUTE = "comics_route"

const val COMICS_STATE_ARGUMENT = "comicsState"

sealed class ComicsScreenRoute(val route:String) {
    object ComicsScreen:ComicsScreenRoute("comics_screen_route/{comicsState}"){
        fun arguments(
            comicsState:String
        ):String = "comics_screen_route/$comicsState"
    }
}