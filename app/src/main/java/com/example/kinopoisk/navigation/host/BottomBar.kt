package com.example.kinopoisk.navigation.host

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomBar(val icon:ImageVector){
    Home(icon = Icons.Default.Home),
    Search(icon = Icons.Default.Search),
    Films(icon = Icons.Default.Menu),
    Profile(icon = Icons.Default.Person)
}