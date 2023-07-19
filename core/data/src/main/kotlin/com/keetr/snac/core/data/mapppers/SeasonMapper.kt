package com.keetr.snac.core.data.mapppers

import com.keetr.snac.core.data.mapppers.tv.toEpisodes
import com.keetr.snac.core.model.Season
import com.keetr.snac.core.model.SeasonWithEpisodes
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.tv.models.SeasonApiModel
import com.keetr.snac.core.network.tv.models.SeasonDetailsApiModel

fun SeasonApiModel.toSeason() = Season(
    id = id,
    name = name,
    overview = overview,
    seasonNumber = seasonNumber,
    episodeCount = episodeCount,
    airDate = airDate?.getYear() ?: "",
    voteAverage = voteAverage.formatTo1dp(),
    posterUrl = Api.BasePosterPath + posterPath
)

fun List<SeasonApiModel>.toSeasons() = map { seasonApiModel -> seasonApiModel.toSeason() }

fun SeasonDetailsApiModel.toSeasonWithEpisodes() = SeasonWithEpisodes(
    id = id,
    name = name,
    overview = overview,
    seasonNumber = seasonNumber,
    episodes = episodes.toEpisodes(),
    airDate = airDate?.formatDate() ?: "",
    posterUrl = Api.BasePosterPath + posterPath
)