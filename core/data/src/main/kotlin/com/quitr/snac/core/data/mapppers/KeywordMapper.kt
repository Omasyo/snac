package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Keyword
import com.quitr.snac.core.network.movie.models.KeywordApiModel

internal fun KeywordApiModel.toKeyword() = Keyword(id, name)

internal fun List<KeywordApiModel>.toKeywords() =
    map { keywordApiModel -> keywordApiModel.toKeyword() }

