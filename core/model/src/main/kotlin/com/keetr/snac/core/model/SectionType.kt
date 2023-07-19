package com.keetr.snac.core.model

import com.keetr.snac.core.model.ShowType.Movie
import com.keetr.snac.core.model.ShowType.Tv

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