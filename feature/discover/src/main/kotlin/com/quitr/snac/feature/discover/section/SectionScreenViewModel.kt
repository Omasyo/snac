package com.quitr.snac.feature.discover.section

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.quitr.snac.core.data.repository.movie.MovieRepository
import com.quitr.snac.core.data.repository.tv.TvRepository
import com.quitr.snac.core.model.SectionType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SectionScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    movieRepository: MovieRepository,
    tvRepository: TvRepository
) : ViewModel() {

    val sectionType: SectionType = checkNotNull(savedStateHandle.get<SectionType>(SectionRoute.sectionType))

    val shows  = when (sectionType) {
        SectionType.MovieTrending -> { movieRepository.getTrendingStream() }
        SectionType.MovieNowPlaying -> { movieRepository.getNowPlayingStream() }
        SectionType.MovieUpcoming -> { movieRepository.getUpcomingStream() }
        SectionType.MoviePopular -> { movieRepository.getPopularStream() }
        SectionType.MovieTopRated -> { movieRepository.getTopRatedStream() }
        SectionType.TvAiringToday -> { tvRepository.getAiringTodayStream() }
        SectionType.TvOnTheAir -> { tvRepository.getOnTheAirStream() }
        SectionType.TvTrending -> { tvRepository.getTrendingStream() }
        SectionType.TvPopular -> { tvRepository.getPopularStream() }
        SectionType.TvTopRated -> { tvRepository.getTopRatedStream() }
    }
}