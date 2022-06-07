package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.IMDb_EXTERNAL_SITE_URL
import com.example.core_network_data.common.ConstantsUrl.IMDb_FILM_AWARDS_URL
import com.example.core_network_data.common.ConstantsUrl.IMDb_FILM_FAQ_URL
import com.example.core_network_data.common.ConstantsUrl.IMDb_FILM_WIKIPEDIA_URL
import com.example.core_network_domain.model.IMDb.FAQ.FAQ
import com.example.core_network_domain.model.IMDb.award.Award
import com.example.core_network_domain.model.IMDb.externalSite.ExternalSite
import com.example.core_network_domain.model.IMDb.wikipedia.Wikipedia
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApi {

    @GET(IMDb_FILM_WIKIPEDIA_URL)
    suspend fun getFilmWikipediaInfo(
        @Path("lang") lang:String = "ru",
        @Path("apiKey") apiKey:String = "k_9adz8m53",
        @Path("id") id:String
    ):Response<Wikipedia>

    @GET(IMDb_FILM_FAQ_URL)
    suspend fun getFilmFAQ(
        @Path("lang") lang:String = "ru",
        @Path("apiKey") apiKey:String = "k_9adz8m53",
        @Path("id") id:String
    ):Response<FAQ>

    @GET(IMDb_FILM_AWARDS_URL)
    suspend fun getFilmAward(
        @Path("lang") lang:String = "ru",
        @Path("apiKey") apiKey:String = "k_9adz8m53",
        @Path("id") id:String
    ):Response<Award>

    @GET(IMDb_EXTERNAL_SITE_URL)
    suspend fun getExternalSites(
        @Path("lang") lang:String = "ru",
        @Path("apiKey") apiKey:String = "k_9adz8m53",
        @Path("id") id:String
    ):Response<ExternalSite>
}