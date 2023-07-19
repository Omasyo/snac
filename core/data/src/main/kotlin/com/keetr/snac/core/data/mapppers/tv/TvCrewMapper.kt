package com.keetr.snac.core.data.mapppers.tv

import com.keetr.snac.core.model.Person
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.tv.models.CrewApiModel
import com.keetr.snac.core.network.tv.models.JobApiModel

private fun List<JobApiModel>.toNames() = map { it.job }

internal fun CrewApiModel.toPerson() =
    Person(id = id, name = name, role = jobs.toNames().joinToString(", "), photoUrl =  Api.BasePosterPath + profilePath)

internal fun List<CrewApiModel>.toPeople() = sortedBy { crew -> -crew.popularity }. map { crew -> crew.toPerson() }