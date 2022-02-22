package com.example.kinopoisk.screen.main.view

data class MenuItemModel(
    val title: String,
    val currentIndex: Int = 0,
    val values: List<String>
)
