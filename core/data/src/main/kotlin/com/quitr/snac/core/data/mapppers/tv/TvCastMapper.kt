package com.quitr.snac.core.data.mapppers.tv

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.CastApiModel
import com.quitr.snac.core.network.tv.models.RoleApiModel

private fun List<RoleApiModel>.toNames() = map { it.character }

internal fun CastApiModel.toPerson() =
    Person(id = id, name = name, role = roles.toNames().joinToString(", "), photoUrl = Api.BaseProfilePath + profilePath)

internal fun List<CastApiModel>.toPeople() = map { cast -> cast.toPerson() }