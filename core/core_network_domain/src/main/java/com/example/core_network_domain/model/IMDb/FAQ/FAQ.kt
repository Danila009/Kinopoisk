package com.example.core_network_domain.model.IMDb.FAQ

data class FAQ(
    val errorMessage: String?,
    val fullTitle: String?,
    val imDbId: String?,
    val items: List<Item>?,
    val spoilerItems: List<SpoilerItem>?,
    val title: String?,
    val type: String?,
    val year: String?
)