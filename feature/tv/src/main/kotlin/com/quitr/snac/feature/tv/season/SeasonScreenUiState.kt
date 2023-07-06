package com.quitr.snac.feature.tv.season

import com.quitr.snac.core.model.SeasonWithEpisodes

internal sealed interface SeasonScreenUiState {
    data class Success(val season: SeasonWithEpisodes): SeasonScreenUiState
    object Loading: SeasonScreenUiState
    data class Error(val message: String): SeasonScreenUiState

}