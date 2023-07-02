package com.quitr.snac.core.network.movie.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieListApiModel(
    @SerialName("page") val page: Int,
    @SerialName("results") val results: List<MovieApiModel>,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("total_results") val totalResults: Int
)