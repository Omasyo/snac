package com.quitr.snac.core.network.tv.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvListApiModel(
    val page: Int,
    val results: List<TvApiModel>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)