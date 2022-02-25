package com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmTopNavGraph.constants

sealed class FilmTopScreenRoute(val route:String){
    object FilmTop: FilmTopScreenRoute("film_top?filmTopName={filmTopName}"){
        fun base(
            filmTopName:String
        ):String = "film_top?filmTopName=$filmTopName"
    }
    object FilmListAdd:FilmTopScreenRoute("film_list_add_screen?filmAdminList={filmAdminList}"){
        fun base(
            filmList:String = ""
        ):String = "film_list_add_screen?filmAdminList=$filmList"
    }
    object FilmListItemAdd:FilmTopScreenRoute("film_list_item_add_screen")
    object AdminListFilmItem: FilmTopScreenRoute("admin_film_list_item_screen?adminFilmListItemId={adminFilmListItemId}"){
        fun base(
            adminFilmListItemId:String
        ):String = "admin_film_list_item_screen?adminFilmListItemId=$adminFilmListItemId"
    }
}
