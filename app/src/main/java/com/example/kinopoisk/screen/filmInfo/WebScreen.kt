package com.example.kinopoisk.screen.filmInfo

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.kinopoisk.navigation.navGraph.cinemaNavGraph.constants.CinemaScreenRoute
import com.example.kinopoisk.navigation.navGraph.filmNavGraph.filmInfoNavGraph.constants.FilmScreenRoute
import com.example.kinopoisk.navigation.navGraph.mainNavGraph.mainNavGraph.constants.MainScreenConstants.Route.MAIN_ROUTE
import com.example.kinopoisk.screen.filmInfo.view.WebView
import com.example.kinopoisk.screen.main.key.WebScreenKey
import com.example.kinopoisk.ui.theme.secondaryBackground
import com.example.kinopoisk.utils.Converters

@Composable
fun WebScreen(
    navController: NavController,
    webUrl:String,
    filmId:String?,
    cinemaId:Int?,
    keyWebScreen:String
) {
    val key = Converters().decodeFromString<WebScreenKey>(keyWebScreen)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = Converters().replaceRange(
                        webUrl,
                        50
                    )
                ) }, navigationIcon = {
                    IconButton(onClick = {
                        when(key){
                            WebScreenKey.PERSON ->  navController.navigate(MAIN_ROUTE)
                            WebScreenKey.FILM ->  filmId?.let {
                                navController.navigate(
                                    FilmScreenRoute.FilmInfo.base(it)
                                )
                            }
                            WebScreenKey.CINEMA -> navController.navigate(
                                CinemaScreenRoute.CinemaInfo.base(
                                    cinemaId = cinemaId!!
                                )
                            )
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = secondaryBackground
                        )
                    }
                }
            )
        }, content = {
            WebView(url = webUrl)
        }
    )
}