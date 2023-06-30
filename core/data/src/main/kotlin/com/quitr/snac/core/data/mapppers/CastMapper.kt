package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.movie.models.CastApiModel
import com.quitr.snac.core.network.people.models.MovieActingCreditApiModel
import com.quitr.snac.core.network.people.models.MovieCreditsApiModel

internal fun CastApiModel.toPerson() =
    Person(id = id, name = name, role = character, photoUrl = Api.BaseProfilePath + profilePath)

internal fun List<CastApiModel>.toPeople() = map { cast -> cast.toPerson() }