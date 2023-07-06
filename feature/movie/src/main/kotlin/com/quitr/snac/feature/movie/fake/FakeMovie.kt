package com.quitr.snac.feature.movie.fake

import com.quitr.snac.core.model.Genre
import com.quitr.snac.core.model.Keyword
import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Person
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType

private val People = List(15) {
    Person(it, "John Cook", "Firefighter", "")
}

private val Shows = List(30) {
    Show(
        it,
        "Son of Sango: The Return From The Evil Forest",
        "9.2",
        "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
        ShowType.Movie
    )
}

internal val FakeMovie = Movie(
    id = 6503,
    backDropUrl = "https://www.google.com/#q=morbi",
    budget = "\$ 100 Million",
    cast = People,
    crew = People,
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
    originalLanguage = "English",
    originalTitle = "Dawn of Sapa: The Poor Shall Breath",
    overview = "detraxit",
    popularity = 0.1,
    posterUrl = "https://duckduckgo.com/?q=accumsan",
    productionCompanies = listOf(
        "21 Laps Entertainment",
        "Monkey Massacre Productions"
    ),
    productionCountries = listOf("United States of America", "New Zealand"),
    recommendations = Shows,
    releaseDate = "15 July 2016",
    revenue = "220",
    runtime = 45,
    similar = Shows,
    spokenLanguages = listOf(),
    status = "Released",
    tagline = "It's how you wear the mask that matters",
    title = "Fantastic Beasts and How to Esacape Them",
    voteAverage = "2.3",
    voteCount = 8974
)