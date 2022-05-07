package com.example.core_network_domain.model.movie.premiere

data class Release(
    val page:Int = 0,
    val total:Int = 1,
    val releases:List<ReleaseItem> = listOf()
)