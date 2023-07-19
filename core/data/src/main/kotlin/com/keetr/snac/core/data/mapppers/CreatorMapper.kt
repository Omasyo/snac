package com.keetr.snac.core.data.mapppers

import com.keetr.snac.core.network.tv.models.CreatedByApiModel

fun List<CreatedByApiModel>.toNames() = map { it.name}