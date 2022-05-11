package com.example.core_network_domain.model.cinema

import com.example.core_network_domain.enum.Week
import com.example.core_network_domain.serialization.TimeSerialization
import kotlinx.serialization.Serializable

@Serializable
data class Schedule(
    val id:Int,
    val week: Week,
    @Serializable(with = TimeSerialization::class)
    val startDate:String,
    @Serializable(with = TimeSerialization::class)
    val endDate:String
)
