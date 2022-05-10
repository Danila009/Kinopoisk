package com.example.core_network_domain.repository

import com.example.core_network_domain.model.IMDb.FAQ.FAQ
import com.example.core_network_domain.model.IMDb.award.Award
import com.example.core_network_domain.model.IMDb.wikipedia.Wikipedia

interface IMDbRepository {

    suspend fun getFilmWikipediaInfo(
        id:String
    ):Wikipedia?

    suspend fun getFilmFAQ(
        id:String
    ):FAQ?

    suspend fun getFilmAward(
        id: String
    ):Award?
}