package com.quitr.snac.feature.tv

import com.quitr.snac.core.model.SeasonWithEpisodes

interface SeasonScreenUiState {
    data class Success(val season: SeasonWithEpisodes): SeasonScreenUiState
    object Loading: SeasonScreenUiState
    data class Error(val message: String): SeasonScreenUiState

}