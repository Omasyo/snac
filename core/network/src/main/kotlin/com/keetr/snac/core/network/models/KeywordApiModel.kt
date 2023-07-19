package com.keetr.snac.core.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeywordApiModel(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)