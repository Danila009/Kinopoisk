package com.example.core_network_domain.model.movie.video

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val kinoPoiskId:Int = 0,
    val total:Int = 0,
    val items:List<VideoItem> = listOf()
)
