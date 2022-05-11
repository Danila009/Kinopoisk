package com.example.core_network_domain.repository

import com.example.core_network_domain.model.cinema.*

interface CinemaRepository {

    suspend fun getCinema(
        search:String,
        has3D:Boolean?,
        has4D:Boolean?,
        hasImax:Boolean?
    ):List<Cinema>

    suspend fun getCinemaById(
        id:Int
    ):Cinema?

    suspend fun getCinemaPhotos(
        id:Int
    ):List<Photo>

    suspend fun getCinemaPhone(
        id:Int
    ):List<Phone>

    suspend fun getCinemaSchedule(
        id: Int
    ):List<Schedule>

    suspend fun getCinemaReview(
        id: Int,
        search: String,
        startDate:String?,
        endDate:String?,
        startRating:Float?,
        endRating:Float?
    ):Review?
}