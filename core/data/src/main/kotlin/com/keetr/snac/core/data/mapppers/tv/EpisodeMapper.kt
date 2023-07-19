package com.keetr.snac.core.data.mapppers.tv

import com.keetr.snac.core.data.mapppers.combineSimilar
import com.keetr.snac.core.data.mapppers.formatDate
import com.keetr.snac.core.data.mapppers.formatTo1dp
import com.keetr.snac.core.model.Episode
import com.keetr.snac.core.model.EpisodeDetails
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.tv.models.EpisodeApiModel

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
        crew = crew?.toPeople()?.combineSimilar() ?: emptyList(),
        guestStars = guestStars?.toPeople()?.combineSimilar() ?: emptyList(),
        stillUrl = Api.BasePosterPath + stillPath,
        runtime = runtime ?: 0,
        voteAverage = voteAverage.formatTo1dp(),
        voteCount = voteCount
    )

fun List<EpisodeApiModel>.toEpisodes() = map { episodeApiModel -> episodeApiModel.toEpisodeDetails() }