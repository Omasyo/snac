package com.keetr.snac.core.data.mapppers

import com.keetr.snac.core.network.models.SpokenLanguageApiModel

internal fun List<SpokenLanguageApiModel>.toNames() =
    map { spokenLanguageApiModel -> spokenLanguageApiModel.englishName }