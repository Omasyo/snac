package com.quitr.snac.core.model

data class SeasonWithEpisodes(
    val id: Int,
    val name: String,
    val overview: String,
    val seasonNumber: Int,
    val episodes: List<EpisodeDetails>,
    val airDate: String,
    val posterUrl: String,
)