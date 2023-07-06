package com.quitr.snac.navigation

import com.quitr.snac.core.model.NavigationRoute
import com.quitr.snac.feature.discover.discover.DiscoverRoute
import com.quitr.snac.feature.movie.MovieCrewRoute
import com.quitr.snac.feature.movie.MovieDetailsRoute
import com.quitr.snac.feature.people.PersonCastRoute
import com.quitr.snac.feature.people.PersonCrewRoute
import com.quitr.snac.feature.people.PersonDetailsRoute
import com.quitr.snac.feature.tv.EpisodeDetailsRoute
import com.quitr.snac.feature.tv.EpisodeCrewRoute
import com.quitr.snac.feature.tv.EpisodeGuestStarsRoute
import com.quitr.snac.feature.tv.TvCastRoute
import com.quitr.snac.feature.tv.TvCrewRoute
import com.quitr.snac.feature.tv.TvDetailsRoute

object SnacRoutes {
    val Root = object : NavigationRoute("root") {
        //        override val root: String
//            get() = "root"

    }

//    val Discover = DiscoverRoute
////    const val library = "library"
//
//    val Section = SectionRoute
//
////    val Movie = MovieDetailsRoute
//    val MovieCast = MovieCastRoute
//    val MovieCrew = MovieCrewRoute

    val Tv = TvDetailsRoute
    val TvCast = TvCastRoute
    val TvCrew = TvCrewRoute
    val TvEpisode = EpisodeDetailsRoute
    val TvEpisodeCrew = EpisodeCrewRoute
    val TvEpisodeGuests = EpisodeGuestStarsRoute

    val Person = PersonDetailsRoute
    val PersonCast = PersonCastRoute
    val PersonCrew = PersonCrewRoute
}