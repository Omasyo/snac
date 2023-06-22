package com.quitr.snac.core.data

import com.quitr.snac.core.data.ShowType.Movie
import com.quitr.snac.core.data.ShowType.Tv

enum class SectionType(val showType: ShowType) {
    MovieTrending(Movie),
    MovieNowPlaying(Movie),
    MovieUpcoming(Movie),
    MoviePopular(Movie),
    MovieTopRated(Movie),
    TvTrending(Tv),
    TvNowPlaying(Tv),
    TvUpcoming(Tv),
    TvPopular(Tv),
    TvTopRated(Tv),
}