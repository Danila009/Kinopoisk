package com.example.kinopoisk.navigation.navGraph.mainNavGraph.sortingFilmNavGraph.constants

import com.example.kinopoisk.screen.main.sortingScreen.tab.OrderTabData
import com.example.kinopoisk.screen.main.sortingScreen.tab.TypeTabData
import com.example.kinopoisk.utils.Converters

sealed class SortingScreenRoute(val route:String){
    object Genre:SortingScreenRoute("genre_screen")
    object Countries:SortingScreenRoute("countries_screen")

    object SortingFilm:SortingScreenRoute("sorting_film_screen?genre={genre}&countries={countries}"){
        fun base(
            genre:String = "All",
            countries:String = "All"
        ):String = "sorting_film_screen?genre=$genre&countries=$countries"
    }
    object ResultSorting:SortingScreenRoute("result_sorting_screen?" +
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
}
