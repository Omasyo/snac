package com.quitr.snac.core.network.movie.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeywordsApiModel(
    @SerialName("keywords") val keywords: List<KeywordApiModel>
)