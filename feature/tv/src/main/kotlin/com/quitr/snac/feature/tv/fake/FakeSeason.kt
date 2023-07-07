package com.quitr.snac.feature.tv.fake

import com.quitr.snac.core.model.Season
import com.quitr.snac.core.model.SeasonWithEpisodes

internal val FakeSeason = Season(
    id = 2,
    name = "Elma Saunders",
    overview = "accumsan",
    seasonNumber = 2950,
    episodeCount = 3386,
    airDate = "accusata",
    voteAverage = "amet",
    posterUrl = "https://www.google.com/#q=massa"
)

internal val FakeSeasonWithEpisodes = SeasonWithEpisodes(
    id = 6175,
    name = "Louisa Reeves",
    overview = "tincidunt",
    seasonNumber = 8588,
    episodes = List(25) { FakeEpisodeDetails.copy(id = it) },
    airDate = "usu",
    posterUrl = "https://duckduckgo.com/?q=bibendum"

)