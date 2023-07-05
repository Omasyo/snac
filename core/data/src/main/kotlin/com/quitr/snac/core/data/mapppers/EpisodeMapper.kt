package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Episode
import com.quitr.snac.core.model.EpisodeDetails
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.EpisodeApiModel

fun EpisodeApiModel.toEpisode() =
    Episode(
    id = id,
    name = name,
    overview = overview,
    episodeNumber = episodeNumber,
    seasonNumber = seasonNumber,
    stillUrl = Api.BasePosterPath + stillPath,
    runtime = runtime ?: 0,
    voteAverage = voteAverage.formatTo1dp(),
    voteCount = voteCount
)

//fun List<EpisodeApiModel>.toEpisodes() = map { episodeApiModel -> episodeApiModel.toEpisode() }

fun EpisodeApiModel.toEpisodeDetails() =
    EpisodeDetails(
        id = id,
        airDate = airDate.formatDate(),
        name = name,
        overview = overview,
        episodeNumber = episodeNumber,
        seasonNumber = seasonNumber,
        crew = crew?.toPeople() ?: emptyList(),
        guestStars = guestStars?.toPeople() ?: emptyList(),
        stillUrl = Api.BasePosterPath + stillPath,
        runtime = runtime ?: 0,
        voteAverage = voteAverage.formatTo1dp(),
        voteCount = voteCount
    )

fun List<EpisodeApiModel>.toEpisodes() = map { episodeApiModel -> episodeApiModel.toEpisodeDetails() }