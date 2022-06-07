package com.example.core_network_data.repository

import com.example.core_network_data.api.IMDbApi
import com.example.core_network_domain.model.IMDb.FAQ.FAQ
import com.example.core_network_domain.model.IMDb.award.Award
import com.example.core_network_domain.model.IMDb.externalSite.ExternalSite
import com.example.core_network_domain.model.IMDb.wikipedia.Wikipedia
import com.example.core_network_domain.repository.IMDbRepository
import retrofit2.Response
import javax.inject.Inject

class IMDbRepositoryImpl @Inject constructor(
    private val imDbApi: IMDbApi
): IMDbRepository {
    override suspend fun getFilmWikipediaInfo(id: String): Wikipedia? {
        return imDbApi.getFilmWikipediaInfo(id = id).body()
    }

    override suspend fun getFilmFAQ(id: String): FAQ? {
        return imDbApi.getFilmFAQ(id = id).body()
    }

    override suspend fun getFilmAward(id: String): Award? {
        return imDbApi.getFilmAward(id = id).body()
    }

    override suspend fun getExternalSites(id: String): Response<ExternalSite> {
        return imDbApi.getExternalSites(id = id)
    }
}