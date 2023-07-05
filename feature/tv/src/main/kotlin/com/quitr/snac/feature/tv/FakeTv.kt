package com.quitr.snac.feature.tv

import com.quitr.snac.core.model.Episode
import com.quitr.snac.core.model.Genre
import com.quitr.snac.core.model.Keyword
import com.quitr.snac.core.model.Person
import com.quitr.snac.core.model.Season
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.model.Tv

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

private val Episode = Episode(
    id = 4912,
    name = "Lonnie Maynard",
    overview = "pretium",
    episodeNumber = 2399,
    seasonNumber = 4254,
    stillUrl = "https://search.yahoo.com/search?p=autem",
    runtime = 8223,
    voteAverage = "constituto",
    voteCount = 1314
)

private val Season = Season(
    id = 2,
    name = "Elma Saunders",
    overview = "accumsan",
    seasonNumber = 2950,
    episodeCount = 3386,
    airDate = "accusata",
    voteAverage = "amet",
    posterUrl = "https://www.google.com/#q=massa"

)

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
    lastEpisodeToAir = Episode,
    name = "Fantastic Beasts and How to Esacape Them",
    networks = listOf(),
    nextEpisodeToAir = Episode,
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
    seasons = List(5) { Season.copy(it) },
    similar = shows,
    spokenLanguages = listOf(),
    status = "Released",
    tagline = "It's how you wear the mask that matters",
    type = "Type",
    voteAverage = "2.3",
    voteCount = 8974
)