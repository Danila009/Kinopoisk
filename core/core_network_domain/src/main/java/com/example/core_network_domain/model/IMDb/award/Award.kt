package com.example.core_network_domain.model.IMDb.award

data class Award(
    val awardsHtml: String? = "",
    val description: String? = "",
    val errorMessage: Any? = "",
    val fullTitle: String? = "",
    val imDbId: String? = "",
    val items: List<Item>? = listOf(),
    val title: String? = "",
    val type: String? = "",
    val year: String? = ""
)