package com.quitr.snac.core.data.mapppers.tv

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.CrewApiModel
import com.quitr.snac.core.network.tv.models.JobApiModel

private fun List<JobApiModel>.toNames() = map { it.job }

internal fun CrewApiModel.toPerson() =
    Person(id = id, name = name, role = jobs.toNames().joinToString(", "), photoUrl =  Api.BasePosterPath + profilePath)

internal fun List<CrewApiModel>.toPeople() = sortedBy { crew -> -crew.popularity }. map { crew -> crew.toPerson() }