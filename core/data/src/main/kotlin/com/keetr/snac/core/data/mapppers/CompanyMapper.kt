package com.keetr.snac.core.data.mapppers

import com.keetr.snac.core.network.models.ProductionCompanyApiModel

internal fun List<ProductionCompanyApiModel>.toNames() =
    map { productionCompanyApiModel -> productionCompanyApiModel.name }