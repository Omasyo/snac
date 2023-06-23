package com.quitr.snac.core.model

import com.quitr.snac.core.model.ShowType.Movie
import com.quitr.snac.core.model.ShowType.Tv

enum class SectionType(val showType: ShowType) {
    MovieTrending(Movie),
    MovieNowPlaying(Movie),
    MovieUpcoming(Movie),
    MoviePopular(Movie),
    MovieTopRated(Movie),
    TvTrending(Tv),
    TvAiringToday(Tv),
    TvOnTheAir(Tv),
    TvPopular(Tv),
    TvTopRated(Tv),
}