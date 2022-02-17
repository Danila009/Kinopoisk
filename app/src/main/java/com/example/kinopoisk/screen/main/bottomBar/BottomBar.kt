package com.example.kinopoisk.screen.main.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.kinopoisk.navigation.BottomScreen

enum class BottomBar(val icon:ImageVector, val nav:String){
    Home(icon = Icons.Default.Home, nav = BottomScreen.Home.route),
    Films(icon = Icons.Default.Menu, nav = BottomScreen.Films.route),
    Persons(icon = Icons.Default.Menu, nav = BottomScreen.Person.route),
    Profile(icon = Icons.Default.Person, nav = BottomScreen.Profile.route)
}
