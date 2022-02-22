package com.example.kinopoisk.api.model.user.history


import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("date")
    val date: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("kinopoiskId")
    val kinopoiskId: Int?,
    @SerializedName("nameRu")
    val nameRu: String?,
    @SerializedName("posterUrlPreview")
    val posterUrlPreview: String?,
    @SerializedName("ratingKinopoisk")
    val ratingKinopoisk: String?
)