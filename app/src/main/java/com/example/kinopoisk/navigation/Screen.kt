package com.example.kinopoisk.navigation

import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.screen.main.sortingScreen.tab.OrderTabData
import com.example.kinopoisk.screen.main.sortingScreen.tab.TypeTabData
import com.example.kinopoisk.utils.Converters

const val ROUTE = "route"
const val MAIN_ROUTE = "main_route"
const val FILM_INFO_ROUTE = "film_info_route"
const val FILM_TOP_ROUTE = "film_top_route"
const val STAFF_INFO_ROUTE = "staff_info_route"
const val MORE_ROUTE = "more_route"
const val REVIEW_ROUTE = "review_route"
const val LOGIN_ROUTE = "login_route"
const val SHOP_ROUTE = "shop_route"
const val CINEMA_ROUTE = "cinema_route"
const val SETTING_ROUTE = "setting_route"

const val RATING_FROM_ARGUMENT = "ratingFrom"
const val RATING_TO_ARGUMENT = "ratingTo"
const val YEAR_FROM_ARGUMENT = "yearFrom"
const val YEAR_TO_ARGUMENT = "yearTo"
const val TYPE_ARGUMENT = "type"
const val ORDER_ARGUMENT = "order"
const val FILM_ID_ARGUMENT = "filmId"
const val FILM_TOP_NAME_ARGUMENT = "filmTopName"
const val STAFF_ID_ARGUMENT = "staffId"
const val GENRE_ARGUMENT = "genre"
const val COUNTRIES_ARGUMENT = "countries"
const val COUNTRIES_ID_ARGUMENT = "countriesId"
const val GENRE_ID_ARGUMENT = "genreId"
const val REVIEW_ID_ARGUMENT = "reviewId"
const val KEY_SCREEN_ARGUMENT = "keyScreen"
const val WEB_URL_ARGUMENT = "webUrl"
const val CINEMA_ID_ARGUMENT = "cinemaId"
const val FILM_LIST_ARGUMENT = "filmList"
const val REVIEW_CINEMA_ID_ARGUMENT = "reviewCinemaId"
const val ADMIN_FILM_LIST_ITEM_ID = "adminFilmListItemId"

sealed class Screen(val route:String) {
    object Authorization:Screen("authorization_screen")
    object Registration:Screen("registration_screen")
    object Main:Screen("main_screen")
    object Sorting:Screen("sorting_screen?genre={genre}&countries={countries}"){
        fun base(
            genre:String = "All",
            countries:String = "All"
        ):String = "sorting_screen?genre=$genre&countries=$countries"
    }
    object UpdateUserPassword:Screen("update_user_password_screen")
    object ResultSorting:Screen("result_sorting_screen?" +
            "ratingFrom={ratingFrom}" +
            "&ratingTo={ratingTo}" +
            "&yearFrom={yearFrom}" +
            "&yearTo={yearTo}" +
            "&type={type}" +
            "&order={order}" +
            "&genreId={genreId}" +
            "&countriesId={countriesId}"
    ){
        fun base(
            ratingFrom:String = "0",
            ratingTo:String = "10",
            yearFrom:String = "1000",
            yearTo:String = "3000",
            type:String = TypeTabData.ALL.name,
            order:String = OrderTabData.RATING.name,
            genreId:String = Converters().encodeToString(listOf<Int>()),
            countriesId:String = Converters().encodeToString(listOf<Int>())
        ):String = "result_sorting_screen?" +
                "ratingFrom=$ratingFrom" +
                "&ratingTo=$ratingTo" +
                "&yearFrom=$yearFrom" +
                "&yearTo=$yearTo" +
                "&type=$type" +
                "&order=$order" +
                "&genreId=$genreId" +
                "&countriesId=$countriesId"
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
    object StaffInfo:Screen("staff_info?staffId={staffId}&filmId={filmId}&keyScreen={keyScreen}"){
        fun base(
            staffId:String,
            filmId:String? = null,
            key:String
        ):String = "staff_info?staffId=$staffId&filmId=$filmId&keyScreen=$key"
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
    object WebScreen:Screen("web_screen?filmId={filmId}&keyScreen={keyScreen}&webUrl={webUrl}&cinemaId={cinemaId}"){
        fun base(
            filmId:String? = null,
            keyString: String,
            webUrl:String,
            cinemaId:String? = null
        ):String = "web_screen?filmId=$filmId&keyScreen=$keyString&webUrl=$webUrl&cinemaId=$cinemaId"
    }
    object Shop:Screen("shop_screen")
    object CinemaMap:Screen("cinema_map_screen")
    object CinemaInfo:Screen("cinema_info_screen?cinemaId={cinemaId}"){
        fun base(
            cinemaId:Int
        ):String = "cinema_info_screen?cinemaId=$cinemaId"
    }
    object AddReviewCinema:Screen("add_review_cinema_screen?cinemaId={cinemaId}"){
        fun base(
            cinemaId:Int
        ):String = "add_review_cinema_screen?cinemaId=$cinemaId"
    }
    object SettingUser:Screen("setting_user_screen")
    object Genre:Screen("genre_screen")
    object Countries:Screen("countries_screen")
    object FilmListAdd:Screen("film_list_add_screen?filmList={filmList}"){
        fun base(
            filmList:String = ""
        ):String = "film_list_add_screen?filmList=$filmList"
    }
    object FilmListItemAdd:Screen("film_list_item_add_screen")
    object AdminListFilmItem:Screen("admin_film_list_item_screen?adminFilmListItemId={adminFilmListItemId}"){
        fun base(
            adminFilmListItemId:String
        ):String = "admin_film_list_item_screen?adminFilmListItemId=$adminFilmListItemId"
    }
    object ShopAddFilmItem:Screen("shop_add_film_item_screen")
}