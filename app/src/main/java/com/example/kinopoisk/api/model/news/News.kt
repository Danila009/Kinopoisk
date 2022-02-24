package com.example.kinopoisk.api.model.news

import com.example.kinopoisk.api.model.cinema.PhotoItem

data class News(
    val id:Int?=null,
    val kinopoiskId:Int,
    val userId:Int,
    val title:String,
    val description:String,
    val date:String,
    val photos:List<PhotoItem>
)
