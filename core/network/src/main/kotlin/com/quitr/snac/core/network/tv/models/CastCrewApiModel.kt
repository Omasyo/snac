package com.quitr.snac.core.network.tv.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastCrewApiModel(
    @SerialName("cast") val cast: List<CastApiModel>,
    @SerialName("crew") val crew: List<CrewApiModel>,
    @SerialName("id") val id: Int
)