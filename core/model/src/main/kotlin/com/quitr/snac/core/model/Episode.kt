package com.quitr.snac.core.model

data class Episode(
    val id: Int,
    val name: String,
    val overview: String,
    val episodeNumber: Int,
    val seasonNumber: Int,
    val posterUrl: String,
    val runtime: Int,
    val voteAverage: String,
    val voteCount: Int,
)