package com.keetr.snac.core.data.mapppers

import com.keetr.snac.core.network.tv.models.NetworkApiModel

fun List<NetworkApiModel>.toNames() = map { it.name }