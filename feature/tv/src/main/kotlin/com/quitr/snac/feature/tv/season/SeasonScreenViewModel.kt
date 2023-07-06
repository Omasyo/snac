package com.quitr.snac.feature.tv.season

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quitr.snac.core.data.repository.tv.TvRepository
import com.quitr.snac.feature.tv.SeasonNumberArg
import com.quitr.snac.feature.tv.TvIdArg
import com.quitr.snac.feature.tv.episode.EpisodeScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SeasonScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    tvRepository: TvRepository
) : ViewModel() {
    private val id = checkNotNull(savedStateHandle.get<Int>(TvIdArg))
    private val seasonNumber = checkNotNull(savedStateHandle.get<Int>(SeasonNumberArg))

    private val _seasonScreenUiState =
        MutableStateFlow<SeasonScreenUiState>(SeasonScreenUiState.Loading)
    val seasonScreenUiState: StateFlow<SeasonScreenUiState> = _seasonScreenUiState

    fun getEpisode(episodeNumber: Int): StateFlow<EpisodeScreenUiState> {
        val episodeScreenUiState =
            MutableStateFlow<EpisodeScreenUiState>(EpisodeScreenUiState.Loading)
        viewModelScope.launch {
            seasonScreenUiState.collect { seasonScreenUiState ->
                if (seasonScreenUiState is SeasonScreenUiState.Success) {
                    val episode =
                        seasonScreenUiState.season.episodes.firstOrNull { episodeDetails ->
                            episodeDetails.episodeNumber == episodeNumber
                        }

                    episodeScreenUiState.value =
                        if (episode != null) EpisodeScreenUiState.Success(episode) else
                            EpisodeScreenUiState.Error("Episode does not exist")
                }
            }
        }
        return episodeScreenUiState
    }

    init {
        viewModelScope.launch {
            _seasonScreenUiState.value = tvRepository.getSeasonDetails(id, seasonNumber)
                .fold({ SeasonScreenUiState.Success(it) }) {
                    SeasonScreenUiState.Error(it.message ?: "")
                }
        }
    }
}