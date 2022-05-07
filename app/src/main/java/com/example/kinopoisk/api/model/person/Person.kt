package com.example.kinopoisk.api.model.person

import com.example.core_network_domain.model.person.PersonItem

data class Person(
    val total:Int,
    val items:List<PersonItem>
)