package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.movie.models.CrewApiModel

internal fun CrewApiModel.toPerson() =
    Person(id = id, name = name, role = job, photoUrl =  Api.BasePosterPath + profilePath)

internal fun List<CrewApiModel>.toPeople() = sortedBy { crew -> -crew.popularity }. map { crew -> crew.toPerson() }