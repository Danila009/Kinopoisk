package com.example.kinopoisk.screen.main.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomBar(val icon:ImageVector){
    Home(Icons.Default.Home),
    Films(Icons.Default.Menu),
    Profile(Icons.Default.Person)
}
