package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.network.movie.models.SpokenLanguageApiModel

internal fun List<SpokenLanguageApiModel>.toNames() =
    map { spokenLanguageApiModel -> spokenLanguageApiModel.englishName }