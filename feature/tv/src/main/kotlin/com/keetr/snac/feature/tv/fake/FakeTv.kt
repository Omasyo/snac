package com.keetr.snac.feature.tv.fake

import com.keetr.snac.core.model.Genre
import com.keetr.snac.core.model.Keyword
import com.keetr.snac.core.model.Person
import com.keetr.snac.core.model.Show
import com.keetr.snac.core.model.ShowType
import com.keetr.snac.core.model.Tv

internal val People = List(15) {
    Person(it, "John Cook", "Firefighter", "")
}

private val shows = List(30) {
    Show(
        it,
        "Son of Sango: The Return From The Evil Forest",
        "9.2",
        "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
        ShowType.Movie
    )
}

internal val FakeTv = Tv(
    id = 6503,
    backdropUrl = "https://www.google.com/#q=morbi",
    creators = listOf("The Evil Monkey", "James The Fly"),
    cast = People,
    crew = People,
    episodeCount = 52,
    firstAirDate = "23 Jun 2002",
    genres = listOf(Genre(1, "Action"), Genre(1, "Adventure"), Genre(1, "Sci-fi")),
    homepageUrl = "https://duckduckgo.com/?q=erroribus",
    inProduction = true,
    keywords = listOf(
        Keyword(1, "cliffhanger"),
        Keyword(2, "steampunk"),
        Keyword(4, "racing"),
        Keyword(5, "sequel"),
        Keyword(3, "dystopian"),
    ),
    languages = listOf("Amasiri", "English", "Latin"),
    lastAirDate = "22 Apr 2022",
    lastEpisodeToAir = FakeEpisode,
    name = "Fantastic Beasts and How to Esacape Them",
    networks = listOf(),
    nextEpisodeToAir = FakeEpisode,
    originalLanguage = "English",
    originalName = "Dawn of Sapa: The Poor Shall Breath",
    originCountry = listOf("Germany", "New Zealand"),
    overview = "detraxit",
    popularity = 0.1,
    posterUrl = "https://duckduckgo.com/?q=accumsan",
    productionCompanies = listOf(
        "21 Laps Entertainment",
        "Monkey Massacre Productions"
    ),
    productionCountries = listOf("United States of America", "New Zealand"),
    recommendations = shows,
    runtime = 45,
    seasonCount = 3,
    seasons = List(5) { FakeSeason.copy(it) },
    similar = shows,
    spokenLanguages = listOf(),
    status = "Released",
    tagline = "It's how you wear the mask that matters",
    type = "Type",
    voteAverage = "2.3",
    voteCount = 8974
)