package com.quitr.snac.core.network.movie.models


import com.quitr.snac.core.network.movie.models.Keyword
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Keywords(
    @SerialName("keywords") val keywords: List<Keyword>
)