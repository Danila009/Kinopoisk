package com.example.core_utils.navigation.filmNavGraph.playlistNavGraph

sealed class PlaylistScreenRoute(val route:String){
    object Playlist: PlaylistScreenRoute("film_top?filmTopName={filmTopName}"){
        fun base(
            filmTopName:String
        ):String = "film_top?filmTopName=$filmTopName"
    }
    object FilmListAdd:PlaylistScreenRoute("film_list_add_screen?filmAdminList={filmAdminList}"){
        fun base(
            filmList:String? = null
        ):String = "film_list_add_screen?filmAdminList=$filmList"
    }
    object FilmListItemAdd:PlaylistScreenRoute("film_list_item_add_screen")
    object AdminListFilmItem: PlaylistScreenRoute("admin_film_list_item_screen?adminFilmListItemId={adminFilmListItemId}"){
        fun base(
            adminFilmListItemId:String
        ):String = "admin_film_list_item_screen?adminFilmListItemId=$adminFilmListItemId"
    }
}
