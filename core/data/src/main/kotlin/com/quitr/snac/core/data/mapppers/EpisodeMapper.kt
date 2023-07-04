package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Episode
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.models.EpisodeApiModel

fun EpisodeApiModel.toEpisode() = Episode(
    id = id,
    name = name,
    overview = overview,
    episodeNumber = episodeNumber,
    seasonNumber = seasonNumber,
    posterUrl = Api.BasePosterPath + stillPath,
    runtime = runtime ?: 0,
    voteAverage = voteAverage.formatTo1dp(),
    voteCount = voteCount

)