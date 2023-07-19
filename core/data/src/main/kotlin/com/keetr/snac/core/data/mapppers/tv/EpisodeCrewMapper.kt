package com.keetr.snac.core.data.mapppers.tv

import com.keetr.snac.core.model.Person
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.tv.models.EpisodeCrewApiModel


internal fun EpisodeCrewApiModel.toPerson() =
    Person(id, name, job, Api.BaseProfilePath + profilePath)

internal fun List<EpisodeCrewApiModel>.toPeople() = map { crewApiModel -> crewApiModel.toPerson() }