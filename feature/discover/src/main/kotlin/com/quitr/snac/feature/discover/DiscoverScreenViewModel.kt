package com.quitr.snac.feature.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quitr.snac.core.data.MovieRepository
import com.quitr.snac.core.data.Response
import com.quitr.snac.core.data.getMovieRepository
import com.quitr.snac.core.data.getOrElse
import com.quitr.snac.core.model.SectionType
import com.quitr.snac.core.model.Show
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DiscoverScreenViewModel(
) : ViewModel() {

    private val movieRepository: MovieRepository = getMovieRepository()

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
                getAndUpdateState(type, movieRepository)
            }
        }
    }

    private suspend fun getAndUpdateState(type: SectionType, repository: MovieRepository) =
        when (type) {
            SectionType.MovieTrending -> {
                _movieTrendingState.value =
                    repository.getTrending(1).getOrElse(SectionUiState.Error) {
                        SectionUiState.Success(it)
                    }
            }

            SectionType.MovieNowPlaying -> {
                _movieNowPlayingState.value =
                    repository.getNowPlaying(1)
                        .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
            }

            SectionType.MovieUpcoming -> {
                _movieUpcomingState.value =
                    repository.getUpcoming(1)
                        .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
            }

            SectionType.MoviePopular -> {
                _moviePopularState.value =
                    repository.getPopular(1)
                        .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
            }

            SectionType.MovieTopRated -> {
                _movieTopRatedState.value =
                    repository.getTopRated(1)
                        .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
            }

            SectionType.TvAiringToday -> {
                _tvAiringTodayState.value =
                    repository.getNowPlaying(1)
                        .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
            }

            SectionType.TvOnTheAir -> {
                _tvOnTheAirState.value =
                    repository.getNowPlaying(1)
                        .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
            }

            SectionType.TvTrending -> {
                _tvTrendingState.value =
                    repository.getNowPlaying(1)
                        .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
            }

            SectionType.TvPopular -> {
                _tvPopularState.value =
                    repository.getNowPlaying(1)
                        .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
            }

            SectionType.TvTopRated -> {
                _tvTopRatedState.value =
                    repository.getNowPlaying(1)
                        .getOrElse(SectionUiState.Error) { SectionUiState.Success(it) }
            }
        }
}

private fun sectionUiState(
    type: SectionType,
    movieRepository: MovieRepository,
) {

}