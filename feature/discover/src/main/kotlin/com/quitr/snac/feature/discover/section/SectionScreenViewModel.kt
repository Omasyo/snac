package com.quitr.snac.feature.discover.section

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.quitr.snac.core.data.MovieRepository
import com.quitr.snac.core.data.TvRepository
import com.quitr.snac.core.data.getOrElse
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.core.model.Show
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SectionScreenViewModel(
    private val sectionType: SectionType,
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) : ViewModel() {

    val shows get()  = when (sectionType) {
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

    companion object {
        fun Factory(
            sectionType: SectionType,
            movieRepository: MovieRepository,
            tvRepository: TvRepository
        ) =
            viewModelFactory {
                initializer {
                    SectionScreenViewModel(sectionType, movieRepository, tvRepository)
                }
            }
    }
}