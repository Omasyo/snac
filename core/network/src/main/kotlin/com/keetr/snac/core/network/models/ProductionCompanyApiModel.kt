package com.keetr.snac.core.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanyApiModel(
    @SerialName("id") val id: Int,
    @SerialName("logo_path") val logoPath: String?,
    @SerialName("name") val name: String,
    @SerialName("origin_country") val originCountry: String
)