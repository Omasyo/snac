package com.quitr.snac.core.network.movie.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryApiModel(
    @SerialName("iso_3166_1") val iso31661: String,
    @SerialName("name") val name: String
)