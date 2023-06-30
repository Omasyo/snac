package com.quitr.snac.navigation

import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.feature.discover.discover.DiscoverRoute
import com.quitr.snac.feature.discover.section.SectionRoute
import com.quitr.snac.feature.movie.MovieCastRoute
import com.quitr.snac.feature.movie.MovieCrewRoute
import com.quitr.snac.feature.movie.MovieDetailsRoute
import com.quitr.snac.feature.people.PersonDetailsRoute

object SnacRoutes {
    val Root = object : NavigationRoute() {
        override val root: String
            get() = "root"

    }

    val Discover = DiscoverRoute
//    const val library = "library"

    val Section = SectionRoute

    val Movie = MovieDetailsRoute
    val MovieCast = MovieCastRoute
    val MovieCrew = MovieCrewRoute

    val Person = PersonDetailsRoute
}