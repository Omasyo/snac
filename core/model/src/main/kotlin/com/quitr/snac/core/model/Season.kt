package com.quitr.snac.core.model

data class Season(
    val id: Int,
    val name: String,
    val overview: String,
    val seasonNumber: Int,
    val episodeCount: Int,
    val airDate: String,
    val voteAverage: String,
    val posterUrl: String,
)