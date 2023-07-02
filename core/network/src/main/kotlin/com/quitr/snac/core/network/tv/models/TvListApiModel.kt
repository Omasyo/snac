package com.quitr.snac.core.network.tv.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvListApiModel(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<TvApiModel>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)