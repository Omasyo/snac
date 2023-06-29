package com.quitr.snac.navigation

import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.feature.discover.discover.DiscoverRoute
import com.quitr.snac.feature.discover.section.SectionRoute
import com.quitr.snac.feature.movie.MovieDetailsRoute

object SnacRoutes {
    val Root = object : NavigationRoute() {
        override val root: String
            get() = "root"

    }

    val Discover = DiscoverRoute
//    const val library = "library"

    val Section = SectionRoute

    val Movie = MovieDetailsRoute
}