package com.keetr.snac.core.network.people.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvCreditsApiModel(
    @SerialName("cast") val cast: List<TvActingCreditApiModel>,
    @SerialName("crew") val crew: List<TvOtherCreditApiModel>,
)