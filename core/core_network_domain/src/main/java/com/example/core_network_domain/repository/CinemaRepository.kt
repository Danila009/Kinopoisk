package com.example.core_network_domain.repository

import com.example.core_network_domain.model.cinema.Cinema

interface CinemaRepository {

    suspend fun getCinema(
        search:String,
        has3D:Boolean?,
        has4D:Boolean?,
        hasImax:Boolean?
    ):List<Cinema>
}