package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Person
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.GuestStar

fun GuestStar.toPerson() = Person(id, name, character, Api.BaseProfilePath + profilePath)

fun List<GuestStar>.toPeople() = map { guestStar -> guestStar.toPerson() }