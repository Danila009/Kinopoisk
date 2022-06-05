package com.example.core_network_domain.repository

import com.example.core_network_domain.model.movie.*
import com.example.core_network_domain.model.movie.budget.Budget
import com.example.core_network_domain.model.movie.distribution.Distribution
import com.example.core_network_domain.model.movie.fact.Fact
import com.example.core_network_domain.model.movie.premiere.Premiere
import com.example.core_network_domain.model.movie.premiere.Release
import com.example.core_network_domain.model.movie.review.Review
import com.example.core_network_domain.model.movie.staff.Staff
import com.example.core_network_domain.model.movie.trailer.Trailer
import com.example.core_network_domain.model.serial.Season
import retrofit2.Response

interface MovieRepository {

    suspend fun getPremiere(year:Int, month: String):Premiere

    suspend fun getRelease(year:Int, month: String, page:Int): Release

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
        page:Int
    ):Film

    suspend fun getFilter():Filter

    suspend fun getFilmInfo(id:Int):retrofit2.Response<FilmInfo>

    suspend fun getSeason(id:Int):Season

    suspend fun getBudget(id: Int):Budget

    suspend fun getFact(id: Int):Fact

    suspend fun getStaff(id: Int):List<Staff>

    suspend fun getSimilar(id: Int):Similar

    suspend fun getSequelAndPrequel(id:Int):List<SequelAndPrequel>

    suspend fun getDistribution(id: Int):Distribution

    suspend fun getImage(id:Int,type:String,page:Int):ImageMovie

    suspend fun getReview(id: Int,page: Int):Review

    suspend fun getTrailer(id: Int): Response<Trailer>
}