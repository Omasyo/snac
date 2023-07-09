package com.quitr.snac.core.data.mapppers.tv

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.EpisodeGuestApiModel

fun EpisodeGuestApiModel.toPerson() = Person(id, name, character, Api.BaseProfilePath + profilePath)

fun List<EpisodeGuestApiModel>.toPeople() = map { guestStar -> guestStar.toPerson() }