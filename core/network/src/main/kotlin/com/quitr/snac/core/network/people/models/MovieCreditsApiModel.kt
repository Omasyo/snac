package com.quitr.snac.core.network.people.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsApiModel(
    @SerialName("cast") val cast: List<MovieActingCreditApiModel>,
    @SerialName("crew") val crew: List<MovieOtherCreditApiModel>,
)