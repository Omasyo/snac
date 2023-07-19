package com.keetr.snac.core.network.movie.models


import com.keetr.snac.core.network.models.KeywordApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeywordsApiModel(
    @SerialName("keywords") val results: List<KeywordApiModel>
)