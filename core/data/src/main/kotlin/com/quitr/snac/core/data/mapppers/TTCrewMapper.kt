package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.TCrewApiModel

fun TCrewApiModel.toPerson() = Person(id, name, job, Api.BaseProfilePath + profilePath)

fun List<TCrewApiModel>.toPeople() = map { crewApiModel -> crewApiModel.toPerson() }