package com.example.core_network_domain.repository

import com.example.core_network_domain.model.IMDb.FAQ.FAQ
import com.example.core_network_domain.model.IMDb.award.Award
import com.example.core_network_domain.model.IMDb.externalSite.ExternalSite
import com.example.core_network_domain.model.IMDb.wikipedia.Wikipedia
import retrofit2.Response

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

    suspend fun getExternalSites(
        id: String
    ):Response<ExternalSite>
}