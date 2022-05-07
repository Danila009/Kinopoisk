package com.example.core_network_domain.model.movie.history

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class HistorySearch(
    val total:Int = 0,
    val items:List<HistorySearchItem> = emptyList()
)