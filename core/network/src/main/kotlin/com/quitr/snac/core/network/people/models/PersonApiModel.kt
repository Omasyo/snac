package com.quitr.snac.core.network.people.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonApiModel(
    @SerialName("adult") val adult: Boolean,
    @SerialName("also_known_as") val alsoKnownAs: List<String>,
    @SerialName("biography") val biography: String,
    @SerialName("birthday") val birthday: String?,
    @SerialName("deathday") val deathday: String?,
    @SerialName("gender") val gender: Int,
    @SerialName("homepage") val homepage: String?,
    @SerialName("id") val id: Int,
    @SerialName("imdb_id") val imdbId: String?,
    @SerialName("known_for_department") val knownForDepartment: String,
    @SerialName("movie_credits") val movieCredits: MovieCreditsApiModel,
    @SerialName("name") val name: String,
    @SerialName("place_of_birth") val placeOfBirth: String?,
    @SerialName("popularity") val popularity: Double,
    @SerialName("profile_path") val profilePath: String?,
    @SerialName("tv_credits") val tvCredits: TvCreditsApiModel
)