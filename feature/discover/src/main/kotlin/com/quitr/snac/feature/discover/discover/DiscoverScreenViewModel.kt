package com.quitr.snac.feature.discover.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.quitr.snac.core.data.MovieRepository
import com.quitr.snac.core.data.TvRepository
import com.quitr.snac.core.data.getOrElse
import com.quitr.snac.core.model.SectionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverScreenViewModel @Inject constructor(
    movieRepository: MovieRepository,
    tvRepository: TvRepository
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

    init {
        viewModelScope.launch {
            for (type in SectionType.values()) {
                getAndUpdateState(type, movieRepository, tvRepository)
            }
        }
    }

    private suspend fun getAndUpdateState(
        type: SectionType,
        movieRepository: MovieRepository,
        tvRepository: TvRepository
    ) = when (type) {
        SectionType.MovieTrending -> {
            _movieTrendingState.value =
                movieRepository.getTrending(1).getOrElse(SectionUiState.Error) {
                    SectionUiState.Success(it)
                }
        }

        SectionType.MovieNowPlaying -> {
            _movieNowPlayingState.value =
                movieRepository.getNowPlaying(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.MovieUpcoming -> {
            _movieUpcomingState.value =
                movieRepository.getUpcoming(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.MoviePopular -> {
            _moviePopularState.value =
                movieRepository.getPopular(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.MovieTopRated -> {
            _movieTopRatedState.value =
                movieRepository.getTopRated(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvAiringToday -> {
            _tvAiringTodayState.value =
                tvRepository.getAiringToday(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvOnTheAir -> {
            _tvOnTheAirState.value =
                tvRepository.getOnTheAir(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvTrending -> {
            _tvTrendingState.value =
                tvRepository.getTrending(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvPopular -> {
            _tvPopularState.value =
                tvRepository.getPopular(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }

        SectionType.TvTopRated -> {
            _tvTopRatedState.value =
                tvRepository.getTopRated(1)
                    .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
        }
    }

    companion object {
        fun Factory(movieRepository: MovieRepository, tvRepository: TvRepository) =
            viewModelFactory {
                initializer {
                    DiscoverScreenViewModel(movieRepository, tvRepository)
                }
            }
    }
}
