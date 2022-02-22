package com.example.kinopoisk.api.model.user

import com.example.kinopoisk.api.model.FilmItem
import com.example.kinopoisk.api.model.user.history.History

data class UserInfo(
    val id:Int = 0,
    val username:String = "",
    val email:String = "",
    val password:String = "",
    val photo:PhotoUser? = null,
    val balance:Int = 0,
    val watchLater:List<FilmItem> = listOf(),
    val favoritFilm:List<FilmItem>? = null,
    val history:List<History>? = null,
    val favoritStaff:List<FavoriteStaff>? = null
)