package com.quitr.snac.core.data.mapppers

import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.movie.list.MovieApiModel
import com.quitr.snac.core.network.movie.models.MovieDetailsApiModel

internal fun MovieApiModel.toShow() = Show(
    id, title, voteAverage.toString(), Api.BasePosterPath + posterPath, ShowType.Movie
)

internal fun List<MovieApiModel>.toShows() = map { movieApiModel -> movieApiModel.toShow() }

internal fun MovieDetailsApiModel.toMovie() = Movie(
    id = id,
    backDropUrl = Api.BaseBackdropPath + backdropPath,
    budget = budget,
    cast = credits.cast.toPeople().combineSimilar(),
    crew = credits.crew.toPeople().combineSimilar(),
    genres = genres.toGenres(),
    homePageUrl = Api.BasePosterPath + homepage,
    imdbId = imdbId ?: "",
    keywords = keywords.keywords.toKeywords(),
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterUrl = Api.BasePosterPath + posterPath,
    productionCompanies = productionCompanies.toNames(),
    productionCountries = productionCountries.toNames(),
    recommendations = recommendations.results.toShows(),
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    similar = similar.results.toShows(),
    spokenLanguages = spokenLanguages.toNames(),
    status = status,
    tagline = tagline,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount
)

