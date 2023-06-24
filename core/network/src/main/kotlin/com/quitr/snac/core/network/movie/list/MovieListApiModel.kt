package com.quitr.snac.core.network.movie.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListApiModel(
    val page: Int,
    val results: List<MovieApiModel>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)