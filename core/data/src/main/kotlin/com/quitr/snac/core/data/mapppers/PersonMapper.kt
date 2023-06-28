package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.movie.models.CastApiModel
import com.quitr.snac.core.network.movie.models.CrewApiModel

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