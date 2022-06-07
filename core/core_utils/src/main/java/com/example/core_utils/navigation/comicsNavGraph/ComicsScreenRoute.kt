package com.example.core_utils.navigation.comicsNavGraph

import com.example.core_utils.state.ComicsState

const val COMICS_ROUTE = "comics_route"

const val COMICS_STATE_ARGUMENT = "comicsState"
const val COMIC_ID_ARGUMENT = "comicId"

sealed class ComicsScreenRoute(val route:String) {
    object ComicsScreen:ComicsScreenRoute("comics_screen_route/{comicsState}"){
        fun arguments(
            comicsState:String
        ):String = "comics_screen_route/$comicsState"
    }
    object ComicInfoScreen:ComicsScreenRoute("comic_info_route/{comicId}?comicsState={comicsState}"){
        fun arguments(
            comicId:Int,
            comicsState:ComicsState
        ):String = "comic_info_route/$comicId?comicsState=${comicsState.name}"
    }
}