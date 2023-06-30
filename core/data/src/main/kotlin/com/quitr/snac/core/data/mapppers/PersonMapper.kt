package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Gender
import com.quitr.snac.core.model.Person
import com.quitr.snac.core.model.PersonDetails
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.movie.models.CastApiModel
import com.quitr.snac.core.network.movie.models.CrewApiModel
import com.quitr.snac.core.network.people.models.PersonApiModel

internal fun PersonApiModel.toPersonDetails() =
    PersonDetails(
        id = id,
        actingCredits = movieCredits.cast.map { it.toCredit() } + tvCredits.cast.map { it.toCredit() },
        adult = adult,
        alsoKnownAs = alsoKnownAs,
        biography = biography,
        birthday = birthday ?: "",
        deathday = deathday ?: "",
        gender = Gender.from(gender),
        homepage = homepage ?: "",
        imdbId = imdbId,
        knownForDepartment = knownForDepartment,
        name = name,
        otherCredits = movieCredits.crew.map { it.toCredit() } + tvCredits.crew.map { it.toCredit() },
        placeOfBirth = placeOfBirth ?: "",
        popularity = popularity,
        profilePath = Api.BaseProfilePath + profilePath
    )

internal fun List<Person>.combineSimilar() = fold(mutableMapOf<Int, Person>()) { result, person ->
    result[person.id] = if (!result.containsKey(person.id)) {
        person
    } else {
        result[person.id]!!.addRole(person.role)
    }
    result
}.map { (key, value) -> value }

private fun Person.addRole(other: String) = copy(
    role = "$role, $other"
)