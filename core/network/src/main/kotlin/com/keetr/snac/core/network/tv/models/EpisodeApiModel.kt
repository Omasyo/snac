package com.keetr.snac.core.network.tv.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeApiModel(
    @SerialName("air_date") val airDate: String?,
    @SerialName("crew") val crew: List<EpisodeCrewApiModel>? = null,
    @SerialName("episode_number") val episodeNumber: Int,
    @SerialName("guest_stars") val guestStars: List<EpisodeGuestApiModel>? = null,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("overview") val overview: String,
    @SerialName("production_code") val productionCode: String,
    @SerialName("runtime") val runtime: Int?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("still_path") val stillPath: String?,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int
)