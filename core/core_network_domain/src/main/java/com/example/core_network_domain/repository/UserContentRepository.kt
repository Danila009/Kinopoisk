package com.example.core_network_domain.repository

import com.example.core_network_domain.model.movie.Movie
import com.example.core_network_domain.model.staff.Staff

interface UserContentRepository {

    suspend fun getUserFavoriteMovie():Movie

    suspend fun getUserWatchLater():Movie

    suspend fun getUserFavoriteStaff():Staff
}