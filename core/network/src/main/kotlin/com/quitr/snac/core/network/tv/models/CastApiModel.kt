package com.quitr.snac.core.network.tv.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CastApiModel(
    @SerialName("adult") val adult: Boolean,
    @SerialName("gender") val gender: Int,
    @SerialName("id") val id: Int,
    @SerialName("known_for_department") val knownForDepartment: String,
    @SerialName("name") val name: String,
    @SerialName("order") val order: Int,
    @SerialName("original_name") val originalName: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("profile_path") val profilePath: String?,
    @SerialName("roles") val roles: List<RoleApiModel>,
    @SerialName("total_episode_count") val totalEpisodeCount: Int
)