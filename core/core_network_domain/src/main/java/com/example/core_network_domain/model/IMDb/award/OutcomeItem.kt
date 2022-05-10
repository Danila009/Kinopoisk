package com.example.core_network_domain.model.IMDb.award

data class OutcomeItem(
    val outcomeCategory: String,
    val outcomeDetails: List<OutcomeDetail>,
    val outcomeTitle: String
)