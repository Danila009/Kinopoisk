package com.example.kinopoisk.api.model.premiere

data class Release(
    val page:Int,
    val total:Int,
    val releases:List<ReleaseItem>
)