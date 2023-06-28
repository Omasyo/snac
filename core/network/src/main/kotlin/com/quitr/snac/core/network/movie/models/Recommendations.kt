package com.quitr.snac.core.network.movie.models


import com.quitr.snac.core.network.movie.models.RecommendationApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Recommendations(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<RecommendationApiModel>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
)