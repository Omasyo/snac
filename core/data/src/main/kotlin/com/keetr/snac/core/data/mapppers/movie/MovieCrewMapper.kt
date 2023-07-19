package com.keetr.snac.core.data.mapppers.movie

import com.keetr.snac.core.model.Person
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.movie.models.CrewApiModel

internal fun CrewApiModel.toPerson() =
    Person(id = id, name = name, role = job, photoUrl = Api.BasePosterPath + profilePath)

@JvmName("CrewApiModelToPeople")
internal fun List<CrewApiModel>.toPeople() =
    sortedBy { crew -> -crew.popularity }.map { crew -> crew.toPerson() }