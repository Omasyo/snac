package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.movie.models.CastApiModel

internal fun CastApiModel.toPerson() =
    Person(id = id, name = name, role = character, photoUrl = profilePath ?: "")

internal fun List<CastApiModel>.toPeople() = map { cast -> cast.toPerson() }