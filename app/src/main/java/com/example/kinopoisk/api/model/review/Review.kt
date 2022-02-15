package com.example.kinopoisk.api.model.review

data class Review(
    val page:Int,
    val filmId:Int,
    val reviewAllCount:Int,
    val reviewAllPositiveRatio:String,
    val reviewPositiveCount:Int,
    val reviewNegativeCount:Int,
    val reviewNeutralCount:Int,
    val pagesCount:Int,
    val reviews:List<ReviewItem>
)