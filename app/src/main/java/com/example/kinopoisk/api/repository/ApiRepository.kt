package com.example.kinopoisk.api.repository

import com.example.kinopoisk.api.ApiKinopoisk
import com.example.kinopoisk.api.model.Film
import com.example.kinopoisk.api.model.FilmInfo
import com.example.kinopoisk.api.model.filmInfo.*
import com.example.kinopoisk.api.model.filmInfo.distribution.Distribution
import com.example.kinopoisk.api.model.review.Review
import com.example.kinopoisk.api.model.review.ReviewDetail
import com.example.kinopoisk.api.model.seasons.Season
import com.example.kinopoisk.api.model.staff.Staff
import com.example.kinopoisk.api.model.staff.StaffInfo
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

    suspend fun getFilmInfo(id:Int):Response<FilmInfo> = apiKinopoisk.getFilmInfo(id)

    suspend fun getBudget(id: Int):Response<Budget> = apiKinopoisk.getBudget(id)

    suspend fun getFact(id: Int):Response<Fact> = apiKinopoisk.getFact(id)

    suspend fun getStaff(id: Int):Response<List<Staff>> = apiKinopoisk.getStaff(id)

    suspend fun getSimilar(id: Int):Response<Similar> = apiKinopoisk.getSimilar(id)

    suspend fun getSequelAndPrequel(id: Int):Response<List<SequelAndPrequel>> = apiKinopoisk.getSequelAndPrequel(id)

    suspend fun getSeason(id: Int):Response<Season> = apiKinopoisk.getSeason(id)

    suspend fun getTop(
        type: String,
        page: Int = 1
    ):Response<Top> = apiKinopoisk.getTop(
        type = type,
        page = page
    )

    suspend fun getImage(
        id: Int,
        type: String,
        page: Int = 1
    ):Response<Image> = apiKinopoisk.getImage(
        id = id,
        type = type,
        page = page
    )

    suspend fun getReview(
        id: Int,
        page: Int = 1
    ):Response<Review> = apiKinopoisk.getReview(
        id = id,
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

    suspend fun getDistribution(
        id: Int
    ):Response<Distribution> = apiKinopoisk.getDistribution(
        id = id
    )
}