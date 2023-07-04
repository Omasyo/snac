package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Season
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.SeasonApiModel

fun SeasonApiModel.toSeason() = Season(
    id = id,
    name = name,
    overview = overview,
    seasonNumber = seasonNumber,
    episodeCount = episodeCount,
    airDate = airDate ?: "",
    voteAverage = voteAverage.formatTo1dp(),
    posterUrl = Api.BasePosterPath + posterPath
)

fun List<SeasonApiModel>.toSeasons() = map { seasonApiModel -> seasonApiModel.toSeason() }