package com.keetr.snac.core.data.mapppers

import com.keetr.snac.core.model.Keyword
import com.keetr.snac.core.network.models.KeywordApiModel

internal fun KeywordApiModel.toKeyword() = Keyword(id, name)

internal fun List<KeywordApiModel>.toKeywords() =
    map { keywordApiModel -> keywordApiModel.toKeyword() }

