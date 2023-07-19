package com.keetr.snac.core.data.mapppers.tv

import com.keetr.snac.core.model.Person
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.tv.models.EpisodeGuestApiModel

fun EpisodeGuestApiModel.toPerson() = Person(id, name, character, Api.BaseProfilePath + profilePath)

fun List<EpisodeGuestApiModel>.toPeople() = map { guestStar -> guestStar.toPerson() }