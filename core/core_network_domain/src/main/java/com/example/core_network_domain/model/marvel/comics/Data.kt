package com.example.core_network_domain.model.marvel.comics

data class Data(
    val count: Int = 0,
    val limit: Int = 0,
    val offset: Int = 0,
    val results: List<Result> = emptyList(),
    val total: Int = 0
)