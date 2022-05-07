package com.example.core_network_domain.useCase.person

import com.example.core_network_domain.model.person.Person
import com.example.core_network_domain.repository.PersonRepository
import javax.inject.Inject

class GetSearchPersonUseCase @Inject constructor(
    private val personRepository: PersonRepository
) {
    suspend operator fun invoke(name:String, page:Int):Person{
        return personRepository.getSearchPerson(name, page)
    }
}