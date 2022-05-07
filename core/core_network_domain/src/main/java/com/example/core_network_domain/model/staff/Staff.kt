package com.example.core_network_domain.model.staff

import kotlinx.serialization.Serializable

@Serializable
data class Staff(
    val total:Int = 0,
    val items:List<StaffItem> = emptyList()
)