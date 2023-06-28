package com.quitr.snac.core.network.movie.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsApiModel(
    @SerialName("cast") val cast: List<CastApiModel>,
    @SerialName("crew") val crew: List<CrewApiModel>
)