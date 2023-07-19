package com.keetr.snac.core.data.mapppers.movie

import com.keetr.snac.core.data.mapppers.combineSimilar
import com.keetr.snac.core.data.mapppers.formatDate
import com.keetr.snac.core.data.mapppers.formatTo1dp
import com.keetr.snac.core.data.mapppers.formatToUsd
import com.keetr.snac.core.data.mapppers.toGenres
import com.keetr.snac.core.data.mapppers.toKeywords
import com.keetr.snac.core.data.mapppers.toNames
import com.keetr.snac.core.model.Movie
import com.keetr.snac.core.model.Show
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.network.Api
import com.keetr.snac.core.network.movie.models.MovieApiModel
import com.keetr.snac.core.network.movie.models.MovieDetailsApiModel

internal fun MovieApiModel.toShow() = Show(
    id, title, voteAverage.formatTo1dp(), Api.BasePosterPath + posterPath, ShowType.Movie
)

internal fun List<MovieApiModel>.toShows() = map { movieApiModel -> movieApiModel.toShow() }

internal fun MovieDetailsApiModel.toMovie() = Movie(
    id = id,
    backDropUrl = Api.BaseBackdropPath + backdropPath,
    budget = budget.formatToUsd(),
    cast = credits.cast.toPeople().combineSimilar(),
    crew = credits.crew.toPeople().combineSimilar(),
    genres = genres.toGenres(),
    homePageUrl = Api.BasePosterPath + homepage,
    imdbId = imdbId ?: "",
    keywords = keywords.results.toKeywords(),
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterUrl = Api.BasePosterPath + posterPath,
    productionCompanies = productionCompanies.toNames(),
    productionCountries = productionCountries.toNames(),
    recommendations = recommendations.results.toShows(),
    releaseDate = releaseDate.formatDate(),
    revenue = revenue.formatToUsd(),
    runtime = runtime,
    similar = similar.results.toShows(),
    spokenLanguages = spokenLanguages.toNames(),
    status = status,
    tagline = tagline,
    title = title,
    voteAverage = voteAverage.formatTo1dp(),
    voteCount = voteCount
)

