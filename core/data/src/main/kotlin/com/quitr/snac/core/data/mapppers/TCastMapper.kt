package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.CastApiModel

internal fun CastApiModel.toPerson() =
    Person(id = id, name = name, role = roles.joinToString(", "), photoUrl = Api.BaseProfilePath + profilePath)

internal fun List<CastApiModel>.toPeople() = map { cast -> cast.toPerson() }