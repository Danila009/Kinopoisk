package com.example.core_utils.navigation.cinemaNavGraph


sealed class CinemaScreenRoute(val route:String){
    object CinemaMap: CinemaScreenRoute("cinema_map_screen")
    object CinemaInfo: CinemaScreenRoute("cinema_info_screen?cinemaId={cinemaId}"){
        fun base(
            cinemaId:Int
        ):String = "cinema_info_screen?cinemaId=$cinemaId"
    }
    object AddReviewCinema: CinemaScreenRoute("add_review_cinema_screen?cinemaId={cinemaId}"){
        fun base(
            cinemaId:Int
        ):String = "add_review_cinema_screen?cinemaId=$cinemaId"
    }
}
