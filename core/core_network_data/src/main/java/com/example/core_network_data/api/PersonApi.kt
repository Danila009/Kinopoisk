package com.example.core_network_data.api

import com.example.core_network_data.common.ConstantsUrl.SEARCH_PERSON_URL
import com.example.core_network_domain.model.person.Person
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApi {

    @GET(SEARCH_PERSON_URL)
    suspend fun getSearchPerson(
        @Query("name") name:String,
        @Query("page") page: Int
    ): Response<Person>
}