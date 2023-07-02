package com.quitr.snac.core.network.tv.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CrewApiModel(
    @SerialName("adult") val adult: Boolean,
    @SerialName("department") val department: String,
    @SerialName("gender") val gender: Int,
    @SerialName("id") val id: Int,
    @SerialName("jobs") val jobs: List<JobApiModel>,
    @SerialName("known_for_department") val knownForDepartment: String,
    @SerialName("name") val name: String,
    @SerialName("original_name") val originalName: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("profile_path") val profilePath: String?,
    @SerialName("total_episode_count") val totalEpisodeCount: Int
)