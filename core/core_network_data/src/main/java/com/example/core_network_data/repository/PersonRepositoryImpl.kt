package com.example.core_network_data.repository

import com.example.core_network_data.api.PersonApi
import com.example.core_network_domain.model.person.Person
import com.example.core_network_domain.repository.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val personApi: PersonApi
): PersonRepository {
    override suspend fun getSearchPerson(name: String, page: Int): Person {
        return personApi.getSearchPerson(name, page).body() ?: Person()
    }
}