package com.quitr.snac.core.model

data class PersonDetails(
    val id: Int,
    val actingCredits: List<Show>,
    val adult: Boolean,
    val alsoKnownAs: List<String>,
    val biography: String,
    val birthday: String,
    val deathday: String,
    val gender: Gender,
    val homepage: String,
    val imdbId: String,
    val knownForDepartment: String,
    val name: String,
    val otherCredits: List<Show>,
    val placeOfBirth: String,
    val popularity: Double,
    val profilePath: String
)
