package com.quitr.snac.core.data.mapppers.tv

import com.quitr.snac.core.data.mapppers.formatTo1dp
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.RecommendationApiModel

internal fun RecommendationApiModel.toShow() = Show(
    id,
    name,
    voteAverage.formatTo1dp(),
    Api.BasePosterPath + posterPath,
    if (mediaType == "tv") ShowType.Tv else ShowType.Movie
)

internal fun List<RecommendationApiModel>.toShows() =
    map { recommendationApiModel -> recommendationApiModel.toShow() }