package com.quitr.snac.core.network.search.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchApiModel(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<SearchResultApiModel>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)