package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.network.models.ProductionCountryApiModel

internal fun List<ProductionCountryApiModel>.toNames() =
    map { productionCountryApiModel -> productionCountryApiModel.name }