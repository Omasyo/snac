package com.quitr.snac.feature.discover.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quitr.snac.core.data.repository.movie.MovieRepository
import com.quitr.snac.core.data.repository.tv.TvRepository
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.core.ui.carousel.ShowCarouselUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _movieTrendingState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val movieTrendingState: StateFlow<ShowCarouselUiState> = _movieTrendingState

    private val _movieNowPlayingState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val movieNowPlayingState: StateFlow<ShowCarouselUiState> = _movieNowPlayingState

    private val _movieUpcomingState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val movieUpcomingState: StateFlow<ShowCarouselUiState> = _movieUpcomingState

    private val _moviePopularState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val moviePopularState: StateFlow<ShowCarouselUiState> = _moviePopularState

    private val _movieTopRatedState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val movieTopRatedState: StateFlow<ShowCarouselUiState> = _movieTopRatedState

    private val _tvTrendingState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val tvTrendingState: StateFlow<ShowCarouselUiState> = _tvTrendingState

    private val _tvAiringTodayState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val tvAiringTodayState: StateFlow<ShowCarouselUiState> = _tvAiringTodayState

    private val _tvOnTheAirState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val tvOnTheAirState: StateFlow<ShowCarouselUiState> = _tvOnTheAirState

    private val _tvPopularState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val tvPopularState: StateFlow<ShowCarouselUiState> = _tvPopularState

    private val _tvTopRatedState = MutableStateFlow<ShowCarouselUiState>(ShowCarouselUiState.Loading)
    val tvTopRatedState: StateFlow<ShowCarouselUiState> = _tvTopRatedState

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
            _movieTrendingState.value = ShowCarouselUiState.Loading
            _movieTrendingState.value =
                movieRepository.getTrending(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }

        SectionType.MovieNowPlaying -> {
            _movieNowPlayingState.value = ShowCarouselUiState.Loading
            _movieNowPlayingState.value =
                movieRepository.getNowPlaying(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }

        SectionType.MovieUpcoming -> {
            _movieUpcomingState.value = ShowCarouselUiState.Loading
            _movieUpcomingState.value =
                movieRepository.getUpcoming(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }

        SectionType.MoviePopular -> {
            _moviePopularState.value = ShowCarouselUiState.Loading
            _moviePopularState.value =
                movieRepository.getPopular(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }

        SectionType.MovieTopRated -> {
            _movieTopRatedState.value = ShowCarouselUiState.Loading
            _movieTopRatedState.value =
                movieRepository.getTopRated(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }

        SectionType.TvAiringToday -> {
            _tvAiringTodayState.value = ShowCarouselUiState.Loading
            _tvAiringTodayState.value =
                tvRepository.getAiringToday(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }

        SectionType.TvOnTheAir -> {
            _tvOnTheAirState.value = ShowCarouselUiState.Loading
            _tvOnTheAirState.value =
                tvRepository.getOnTheAir(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }

        SectionType.TvTrending -> {
            _tvTrendingState.value = ShowCarouselUiState.Loading
            _tvTrendingState.value =
                tvRepository.getTrending(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }

        SectionType.TvPopular -> {
            _tvPopularState.value = ShowCarouselUiState.Loading
            _tvPopularState.value =
                tvRepository.getPopular(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }

        SectionType.TvTopRated -> {
            _tvTopRatedState.value = ShowCarouselUiState.Loading
            _tvTopRatedState.value =
                tvRepository.getTopRated(1)
                    .fold({ ShowCarouselUiState.Success(it) }) { ShowCarouselUiState.Error }
        }
    }
}
