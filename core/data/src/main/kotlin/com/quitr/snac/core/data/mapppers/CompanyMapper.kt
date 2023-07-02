package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.network.models.ProductionCompanyApiModel

internal fun List<ProductionCompanyApiModel>.toNames() =
    map { productionCompanyApiModel -> productionCompanyApiModel.name }