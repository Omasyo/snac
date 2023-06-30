package com.quitr.snac.core.model

data class Credit(
    val id: String,
    val role: String,
    val title: String,
    val rating: String,
    val posterUrl: String,
    val showType: ShowType,
    internal val relevance: Double,
)
