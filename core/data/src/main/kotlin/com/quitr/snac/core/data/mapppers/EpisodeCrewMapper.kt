package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.EpisodeCrewApiModel


internal fun EpisodeCrewApiModel.toPerson() =
    Person(id, name, job, Api.BaseProfilePath + profilePath)

internal fun List<EpisodeCrewApiModel>.toPeople() = map { crewApiModel -> crewApiModel.toPerson() }