package com.example.kinopoisk.navigation.navGraph.filmNavGraph.reviewFilmNavGraph.constants


sealed class ReviewFilmScreenRoute(val route:String){
    object ReviewDetail: ReviewFilmScreenRoute("review_detail?reviewId={reviewId}&filmId={filmId}"){
        fun base(
            reviewId:String,
            filmId:String
        ):String = "review_detail?reviewId=$reviewId&filmId=$filmId"
    }
}
