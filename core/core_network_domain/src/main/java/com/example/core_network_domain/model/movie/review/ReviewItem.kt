package com.example.core_network_domain.model.movie.review

data class ReviewItem(
    val reviewId:Int,
    val reviewType:String,
    val reviewData:String,
    val userPositiveRating:String,
    val userNegativeRating:String,
    val reviewAutor:String,
    val reviewTitle:String,
    val reviewDescription:String
)