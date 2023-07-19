package com.keetr.snac.core.data.mapppers.movie

import com.keetr.snac.core.data.mapppers.formatTo1dp
import com.keetr.snac.core.model.Show
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.movie.models.RecommendationApiModel

internal fun RecommendationApiModel.toShow() = Show(
    id,
    title,
    voteAverage.formatTo1dp(),
    Api.BasePosterPath + posterPath,
    if (mediaType == "tv") ShowType.Tv else ShowType.Movie
)

internal fun List<RecommendationApiModel>.toShows() =
    map { recommendationApiModel -> recommendationApiModel.toShow() }