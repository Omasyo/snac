package com.keetr.snac.core.data.mapppers.movie

import com.keetr.snac.core.model.Person
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.movie.models.CastApiModel

internal fun CastApiModel.toPerson() =
    Person(id = id, name = name, role = character, photoUrl = Api.BaseProfilePath + profilePath)

internal fun List<CastApiModel>.toPeople() = map { cast -> cast.toPerson() }