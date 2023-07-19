package com.keetr.snac.core.model

data class EpisodeDetails(
    val id: Int,
    val airDate: String,
    val name: String,
    val overview: String,
    val episodeNumber: Int,
    val seasonNumber: Int,
    val crew: List<Person>,
    val guestStars: List<Person>,
    val stillUrl: String,
    val runtime: Int,
    val voteAverage: String,
    val voteCount: Int,
)