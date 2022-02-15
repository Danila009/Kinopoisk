package com.example.kinopoisk.api.model.staff


import com.google.gson.annotations.SerializedName

data class StaffInfo(
    @SerializedName("age")
    val age: Int? = null,
    @SerializedName("birthday")
    val birthday: String? = null,
    @SerializedName("birthplace")
    val birthplace: String? = null,
    @SerializedName("death")
    val death: String? = null,
    @SerializedName("deathplace")
    val deathplace: String? = null,
    @SerializedName("facts")
    val facts: List<String>? = null,
    @SerializedName("films")
    val films: List<FilmStaff> = listOf(),
    @SerializedName("growth")
    val growth: String? = null,
    @SerializedName("hasAwards")
    val hasAwards: Int? = null,
    @SerializedName("nameEn")
    val nameEn: String? = null,
    @SerializedName("nameRu")
    val nameRu: String? = null,
    @SerializedName("personId")
    val personId: Int? = null,
    @SerializedName("posterUrl")
    val posterUrl: String? = null,
    @SerializedName("profession")
    val profession: String? = null,
    @SerializedName("sex")
    val sex: String? = null,
    @SerializedName("spouses")
    val spouses: List<SpouseStaff>? = null,
    @SerializedName("webUrl")
    val webUrl: String? = null
)