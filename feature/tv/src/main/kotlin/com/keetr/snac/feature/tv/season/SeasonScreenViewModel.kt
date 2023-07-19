package com.keetr.snac.feature.tv.season

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keetr.snac.core.data.repository.tv.TvRepository
import com.keetr.snac.feature.tv.SeasonNumberArg
import com.keetr.snac.feature.tv.TvIdArg
import com.keetr.snac.feature.tv.episode.EpisodeDetailScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SeasonScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    tvRepository: TvRepository
) : ViewModel() {
    val tvId = checkNotNull(savedStateHandle.get<Int>(TvIdArg))
    private val seasonNumber = checkNotNull(savedStateHandle.get<Int>(SeasonNumberArg))

    private val _seasonScreenUiState =
        MutableStateFlow<SeasonScreenUiState>(SeasonScreenUiState.Loading)
    val seasonScreenUiState: StateFlow<SeasonScreenUiState> = _seasonScreenUiState

    fun getEpisode(episodeNumber: Int): StateFlow<EpisodeDetailScreenUiState> {
        val episodeDetailScreenUiState =
            MutableStateFlow<EpisodeDetailScreenUiState>(EpisodeDetailScreenUiState.Loading)
        viewModelScope.launch {
            seasonScreenUiState.collect { seasonScreenUiState ->
                if (seasonScreenUiState is SeasonScreenUiState.Success) {
                    val episode =
                        seasonScreenUiState.season.episodes.firstOrNull { episodeDetails ->
                            episodeDetails.episodeNumber == episodeNumber
                        }

                    Log.d(
                        "TAG",
                        "getEpisode: No Bullshit epNo $episodeNumber episode $episode season ${seasonScreenUiState.season}"
                    )

                    episodeDetailScreenUiState.value =
                        if (episode != null) EpisodeDetailScreenUiState.Success(episode) else
                            EpisodeDetailScreenUiState.Error("Episode does not exist")
                }
            }
        }
        return episodeDetailScreenUiState
    }

    init {
        viewModelScope.launch {
            _seasonScreenUiState.value = tvRepository.getSeasonDetails(tvId, seasonNumber)
                .fold({ SeasonScreenUiState.Success(it) }) {
                    SeasonScreenUiState.Error(it.message ?: "")
                }
        }
    }
}