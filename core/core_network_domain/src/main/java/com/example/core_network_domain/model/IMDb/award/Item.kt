package com.example.core_network_domain.model.IMDb.award

data class Item(
    val eventTitle: String,
    val eventYear: String,
    val outcomeItems: List<OutcomeItem>
)