package com.quitr.snac.core.network.tv.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JobApiModel(
    @SerialName("credit_id") val creditId: String,
    @SerialName("episode_count") val episodeCount: Int,
    @SerialName("job") val job: String
)