package com.quitr.snac.core.network.tv.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedByApiModel(
    @SerialName("credit_id") val creditId: String,
    @SerialName("gender") val gender: Int,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("profile_path") val profilePath: String
)