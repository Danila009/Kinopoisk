package com.example.core_network_domain.model.movie.history

import com.example.core_network_domain.serialization.DateTimeSerialization
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@Serializable
data class HistorySearchItem(
    val id:Int,
    val title:String,
    @Serializable(with = DateTimeSerialization::class)
    val date:String
)