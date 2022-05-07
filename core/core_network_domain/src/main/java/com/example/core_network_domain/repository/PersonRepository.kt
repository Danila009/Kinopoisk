package com.example.core_network_domain.repository

import com.example.core_network_domain.model.person.Person

interface PersonRepository {

    suspend fun getSearchPerson(
        name:String,
        page:Int
    ):Person
}