package com.keetr.snac.core.network.tv.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoleApiModel(
    @SerialName("character") val character: String,
    @SerialName("credit_id") val creditId: String,
    @SerialName("episode_count") val episodeCount: Int
)