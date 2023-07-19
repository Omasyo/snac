package com.keetr.snac.core.data.mapppers.tv

import com.keetr.snac.core.data.mapppers.combineSimilar
import com.keetr.snac.core.data.mapppers.formatDate
import com.keetr.snac.core.data.mapppers.formatTo1dp
import com.keetr.snac.core.data.mapppers.toGenres
import com.keetr.snac.core.data.mapppers.toKeywords
import com.keetr.snac.core.data.mapppers.toNames
import com.keetr.snac.core.data.mapppers.toSeasons
import com.keetr.snac.core.model.Show
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.model.Tv
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.tv.models.TvApiModel
import com.keetr.snac.core.network.tv.models.TvDetailsApiModel


internal fun TvApiModel.toShow() =
    Show(id, name, voteAverage.formatTo1dp(), Api.BasePosterPath + posterPath, ShowType.Tv)

internal fun List<TvApiModel>.toShows() = map { tvApiModel -> tvApiModel.toShow() }

internal fun TvDetailsApiModel.toTv() = Tv(
    id = id,
    backdropUrl = Api.BaseBackdropPath + backdropPath,
    creators = createdBy.toNames(),
    cast = credits.cast.toPeople().combineSimilar(),
    crew = credits.crew.toPeople().combineSimilar(),
    episodeCount = numberOfEpisodes,
    firstAirDate = firstAirDate.formatDate(),
    genres = genres.toGenres(),
    homepageUrl = homepage,
    inProduction = inProduction,
    keywords = keywords.results.toKeywords(),
    languages = languages,
    lastAirDate = lastAirDate.formatDate(),
    lastEpisodeToAir = lastEpisodeToAir.toEpisode(),
    name = name,
    networks = networks.toNames(),
    nextEpisodeToAir = nextEpisodeToAir?.toEpisode(),
    originCountry = originCountry,
    originalLanguage = originalLanguage,
    originalName = originalName,
    overview = overview,
    popularity = popularity,
    posterUrl = Api.BasePosterPath + posterPath,
    productionCompanies = productionCompanies.toNames(),
    productionCountries = productionCountries.toNames(),
    recommendations = recommendations.results.toShows(),
    runtime = episodeRunTime.firstOrNull() ?: 0,
    seasonCount = numberOfSeasons,
    seasons = seasons.toSeasons(),
    similar = similar.results.toShows(),
    spokenLanguages = spokenLanguages.toNames(),
    status = status,
    tagline = tagline,
    type = type,
    voteAverage = voteAverage.formatTo1dp(),
    voteCount = voteCount
)