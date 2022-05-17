package com.example.kinopoisk.api.repository

import com.example.kinopoisk.api.ApiKinopoisk
import com.example.kinopoisk.api.model.Film
import com.example.core_network_domain.model.movie.review.ReviewDetail
import com.example.core_network_domain.model.movie.staff.StaffInfo
import com.example.kinopoisk.api.model.topFilm.Top
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiKinopoisk: ApiKinopoisk
) {
    suspend fun getFilm(
        genres:List<Int>,
        countries:List<Int>,
        order:String,
        type:String,
        ratingFrom:Int,
        ratingTo:Int,
        yearFrom:Int,
        yearTo:Int,
        keyword:String,
        page:Int = 1
    ):Response<Film> = apiKinopoisk.getFilm(
        genres = genres,
        countries = countries,
        order = order,
        type = type,
        ratingFrom = ratingFrom,
        ratingTo =ratingTo,
        yearFrom = yearFrom,
        yearTo = yearTo,
        keyword = keyword,
        page = page
    )

    suspend fun getTop(
        type: String,
        page: Int = 1
    ):Response<Top> = apiKinopoisk.getTop(
        type = type,
        page = page
    )

    suspend fun getStaffInfo(
        id: Int
    ):Response<StaffInfo> = apiKinopoisk.getStaffInfo(
        id = id
    )

    suspend fun getReviewDetail(
        id: Int
    ):Response<ReviewDetail> = apiKinopoisk.getReviewDetail(
        id = id
    )
}