package com.quitr.snac.core.data


sealed interface SectionType {
    enum class Movie: SectionType {
        Trending,
        NowPlaying,
        Upcoming,
        Popular,
        TopRated,
    }
    enum class Tv: SectionType {
        Trending,
        NowPlaying,
        Upcoming,
        Popular,
        TopRated,
    }
}