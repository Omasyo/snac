package com.keetr.snac.feature.discover.section

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.keetr.snac.core.data.repository.movie.MovieRepository
import com.keetr.snac.core.data.repository.tv.TvRepository
import com.keetr.snac.core.model.SectionType
import com.keetr.snac.core.model.Show
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
internal class SectionScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) : ViewModel() {

    val sectionType: SectionType = checkNotNull(savedStateHandle.get<SectionType>(SectionTypeArg))

    private lateinit var _shows: Flow<PagingData<Show>>
    val shows get() = _shows

    fun refresh() {
            _shows = when (sectionType) {
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
        }.cachedIn(viewModelScope)
    }

    init {
        refresh()
    }
}