package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.network.tv.models.NetworkApiModel

fun List<NetworkApiModel>.toNames() = map { it.name }