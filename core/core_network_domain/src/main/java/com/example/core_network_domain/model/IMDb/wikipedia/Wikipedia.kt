package com.example.core_network_domain.model.IMDb.wikipedia

data class Wikipedia(
    val errorMessage: String?,
    val fullTitle: String?,
    val imDbId: String?,
    val language: String?,
    val plotFull: PlotFull?,
    val plotShort: PlotShort?,
    val title: String?,
    val titleInLanguage: String?,
    val type: String?,
    val url: String?,
    val year: String?
)