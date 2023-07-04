package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.network.tv.models.CreatedByApiModel

fun List<CreatedByApiModel>.toNames() = map { it.name}