package com.quitr.snac.feature.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.quitr.snac.core.data.MovieRepository
import com.quitr.snac.core.data.TvRepository
import com.quitr.snac.core.data.getOrElse
import com.quitr.snac.core.model.SectionType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SectionScreenViewModel(
    private val sectionType: SectionType,
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _sectionScreenUiState =
        MutableStateFlow<SectionScreenUiState>(SectionScreenUiState.Loading)
    val sectionScreenUiState: StateFlow<SectionScreenUiState> = _sectionScreenUiState

    init {
        viewModelScope.launch {
//            addNextPage()
            _sectionScreenUiState.value =
                getState(1).getOrElse(
                    SectionScreenUiState.Error
                ) { SectionScreenUiState.Success(it, 1, SectionScreenUiState.PagingState.Idle) }
        }
    }

    fun addNextPage() {
//        println("Current page is $temp")
        val t = (_sectionScreenUiState.value as SectionScreenUiState.Success)
        _sectionScreenUiState.value = t.copy(state = SectionScreenUiState.PagingState.Loading)
        viewModelScope.launch {
            _sectionScreenUiState.value =
                getState(t.page + 1).getOrElse(
                    SectionScreenUiState.Error
                ) { shows ->
                    t + shows
                }
        }
    }

    private suspend fun getState(
//        type: SectionType,
        page: Int,
//        movieRepository: MovieRepository,
//        tvRepository: TvRepository
    ) = when (sectionType) {
        SectionType.MovieTrending -> {
            movieRepository.getTrending(page)
        }

        SectionType.MovieNowPlaying -> {
            movieRepository.getNowPlaying(page)

        }

        SectionType.MovieUpcoming -> {
            movieRepository.getUpcoming(page)

        }

        SectionType.MoviePopular -> {
            movieRepository.getPopular(page)

        }

        SectionType.MovieTopRated -> {
            movieRepository.getTopRated(page)

        }

        SectionType.TvAiringToday -> {
            tvRepository.getAiringToday(page)

        }

        SectionType.TvOnTheAir -> {
            tvRepository.getOnTheAir(page)

        }

        SectionType.TvTrending -> {
            tvRepository.getTrending(page)

        }

        SectionType.TvPopular -> {
            tvRepository.getPopular(page)

        }

        SectionType.TvTopRated -> {
            tvRepository.getTopRated(page)

        }
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