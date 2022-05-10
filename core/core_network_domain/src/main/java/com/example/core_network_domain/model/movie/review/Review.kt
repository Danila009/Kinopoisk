package com.example.core_network_domain.model.movie.review

data class Review(
    val page:Int = 0,
    val filmId:Int = 0,
    val reviewAllCount:Int = 0,
    val reviewAllPositiveRatio:String = "",
    val reviewPositiveCount:Int = 0,
    val reviewNegativeCount:Int = 0,
    val reviewNeutralCount:Int = 0,
    val pagesCount:Int = 0,
    val reviews:List<ReviewItem> = emptyList()
)