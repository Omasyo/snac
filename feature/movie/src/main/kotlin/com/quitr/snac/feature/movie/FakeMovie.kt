package com.quitr.snac.feature.movie

import com.quitr.snac.core.model.Genre
import com.quitr.snac.core.model.Keyword
import com.quitr.snac.core.model.Movie

internal val FakeMovie = Movie(
    id = 6503,
    backDropUrl = "https://www.google.com/#q=morbi",
    budget = 1777,
    cast = listOf(),
    crew = listOf(),
    genres = listOf(Genre(1, "Action"), Genre(1, "Adventure"), Genre(1, "Sci-fi")),
    homePageUrl = "https://duckduckgo.com/?q=erroribus",
    imdbId = "torquent",
    keywords = listOf(
        Keyword(1, "cliffhanger"),
        Keyword(2, "steampunk"),
        Keyword(4, "racing"),
        Keyword(5, "sequel"),
        Keyword(3, "dystopian"),
    ),
    originalLanguage = "augue",
    originalTitle = "venenatis",
    overview = "detraxit",
    popularity = 0.1,
    posterUrl = "https://duckduckgo.com/?q=accumsan",
    productionCompanies = listOf(),
    productionCountries = listOf(),
    recommendations = listOf(),
    releaseDate = "ante",
    revenue = 4452,
    runtime = 6162,
    similar = listOf(),
    spokenLanguages = listOf(),
    status = "lacus",
    tagline = "It's how you wear the mask that matters",
    title = "Fantastic Beasts and How to Esacape Them",
    voteAverage = 2.3,
    voteCount = 8974
)