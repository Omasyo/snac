package com.quitr.snac.feature.discover.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quitr.snac.core.data.movie.MovieRepository
import com.quitr.snac.core.data.tv.TvRepository
import com.quitr.snac.core.data.getOrElse
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.core.ui.section.SectionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _movieTrendingState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val movieTrendingState: StateFlow<SectionUiState> = _movieTrendingState

    private val _movieNowPlayingState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val movieNowPlayingState: StateFlow<SectionUiState> = _movieNowPlayingState

    private val _movieUpcomingState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val movieUpcomingState: StateFlow<SectionUiState> = _movieUpcomingState

    private val _moviePopularState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val moviePopularState: StateFlow<SectionUiState> = _moviePopularState

    private val _movieTopRatedState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val movieTopRatedState: StateFlow<SectionUiState> = _movieTopRatedState

    private val _tvTrendingState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val tvTrendingState: StateFlow<SectionUiState> = _tvTrendingState

    private val _tvAiringTodayState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val tvAiringTodayState: StateFlow<SectionUiState> = _tvAiringTodayState

    private val _tvOnTheAirState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val tvOnTheAirState: StateFlow<SectionUiState> = _tvOnTheAirState

    private val _tvPopularState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val tvPopularState: StateFlow<SectionUiState> = _tvPopularState

    private val _tvTopRatedState = MutableStateFlow<SectionUiState>(SectionUiState.Loading)
    val tvTopRatedState: StateFlow<SectionUiState> = _tvTopRatedState

    fun retrySectionFetch(sectionType: SectionType) =
        viewModelScope.launch { getAndUpdateState(sectionType) }

    init {
        viewModelScope.launch {
            for (type in SectionType.values()) {
                launch { getAndUpdateState(type) }
            }
        }
    }

    private suspend fun getAndUpdateState(
        type: SectionType
    ) = when (type) {
        SectionType.MovieTrending -> {
            _movieTrendingState.value = SectionUiState.Loading
            _movieTrendingState.value =
                movieRepository.getTrending(1).getOrElse(SectionUiState.Error) {
                    SectionUiState.Success(it)
                }
        }

        SectionType.MovieNowPlaying -> {
            _movieNowPlayingState.value = SectionUiState.Loading
            _movieNowPlayingState.value =
                movieRepository.getNowPlaying(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.MovieUpcoming -> {
            _movieUpcomingState.value = SectionUiState.Loading
            _movieUpcomingState.value =
                movieRepository.getUpcoming(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.MoviePopular -> {
            _moviePopularState.value = SectionUiState.Loading
            _moviePopularState.value =
                movieRepository.getPopular(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.MovieTopRated -> {
            _movieTopRatedState.value = SectionUiState.Loading
            _movieTopRatedState.value =
                movieRepository.getTopRated(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvAiringToday -> {
            _tvAiringTodayState.value = SectionUiState.Loading
            _tvAiringTodayState.value =
                tvRepository.getAiringToday(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvOnTheAir -> {
            _tvOnTheAirState.value = SectionUiState.Loading
            _tvOnTheAirState.value =
                tvRepository.getOnTheAir(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvTrending -> {
            _tvTrendingState.value = SectionUiState.Loading
            _tvTrendingState.value =
                tvRepository.getTrending(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvPopular -> {
            _tvPopularState.value = SectionUiState.Loading
            _tvPopularState.value =
                tvRepository.getPopular(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvTopRated -> {
            _tvTopRatedState.value = SectionUiState.Loading
            _tvTopRatedState.value =
                tvRepository.getTopRated(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }
    }
}
