package com.example.core_utils.navigation.filmNavGraph.reviewFilmNavGraph


sealed class ReviewFilmScreenRoute(val route:String){
    object ReviewDetail: ReviewFilmScreenRoute("review_detail?reviewId={reviewId}&filmId={filmId}"){
        fun base(
            reviewId:String,
            filmId:String
        ):String = "review_detail?reviewId=$reviewId&filmId=$filmId"
    }
}
