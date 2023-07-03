package com.quitr.snac.core.network.tv.models

import com.quitr.snac.core.network.models.KeywordApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KeywordsApiModel(
    @SerialName("results") val results: List<KeywordApiModel>
)
