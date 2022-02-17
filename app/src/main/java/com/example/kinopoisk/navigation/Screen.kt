package com.example.kinopoisk.navigation

const val ROUTE = "route"
const val MAIN_ROUTE = "main_route"
const val FILM_INFO_ROUTE = "film_info_route"
const val FILM_TOP_ROUTE = "film_top_route"
const val STAFF_INFO_ROUTE = "staff_info_route"
const val MORE_ROUTE = "more_route"
const val REVIEW_ROUTE = "review_route"
const val LOGIN_ROUTE = "login_route"

const val RATING_FROM_ARGUMENT = "ratingFrom"
const val RATING_TO_ARGUMENT = "ratingTo"
const val YEAR_FROM_ARGUMENT = "yearFrom"
const val YEAR_TO_ARGUMENT = "yearTo"
const val FILM_ID_ARGUMENT = "filmId"
const val FILM_TOP_NAME_ARGUMENT = "filmTopName"
const val STAFF_ID_ARGUMENT = "staffId"
const val REVIEW_ID_ARGUMENT = "reviewId"

sealed class Screen(val route:String) {
    object Authorization:Screen("authorization_screen")
    object Registration:Screen("registration_screen")
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
    object FilmTop:Screen("film_top?filmTopName={filmTopName}"){
        fun base(
            filmTopName:String
        ):String = "film_top?filmTopName=$filmTopName"
    }
    object StaffInfo:Screen("staff_info?staffId={staffId}&filmId={filmId}"){
        fun base(
            staffId:String,
            filmId:String
        ):String = "staff_info?staffId=$staffId&filmId=$filmId"
    }
    object MoreStaff:Screen("more_staff?staffId={staffId}&filmId={filmId}"){
        fun base(
            staffIf: String,
            filmId:String
        ):String = "more_staff?staffId=$staffIf&filmId=$filmId"
    }
    object ImageMore:Screen("image_more_screen?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "image_more_screen?filmId=$filmId"
    }
    object ReviewMore:Screen("review_more_screen?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "review_more_screen?filmId=$filmId"
    }
    object ReviewDetail:Screen("review_detail?reviewId={reviewId}&filmId={filmId}"){
        fun base(
            reviewId:String,
            filmId:String
        ):String = "review_detail?reviewId=$reviewId&filmId=$filmId"
    }
    object WebScreen:Screen("web_screen?filmId={filmId}"){
        fun base(
            filmId:String
        ):String = "web_screen?filmId=$filmId"
    }
}

sealed class BottomScreen(val route: String){
    object Home:BottomScreen("home_screen")
    object Films:BottomScreen("film_screen")
    object Profile:BottomScreen("profile_screen")
}