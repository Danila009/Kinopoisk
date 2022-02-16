package com.example.kinopoisk.api.model.review

data class ReviewDetail(
    val reviewId:Int = 0,
    val reviewType:String = "",
    val reviewData:String = "",
    val userPositiveRating:Int = 0,
    val userNegativeRating:Int = 0,
    val reviewAutor:String = "",
    val reviewTitle:String = "",
    val reviewDescription:String = ""
)
