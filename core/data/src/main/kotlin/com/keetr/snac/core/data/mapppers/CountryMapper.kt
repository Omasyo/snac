package com.keetr.snac.core.data.mapppers

import com.keetr.snac.core.network.models.ProductionCountryApiModel

internal fun List<ProductionCountryApiModel>.toNames() =
    map { productionCountryApiModel -> productionCountryApiModel.name }