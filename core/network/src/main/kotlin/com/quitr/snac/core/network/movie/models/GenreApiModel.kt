package com.quitr.snac.core.network.movie.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreApiModel(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)