package com.keetr.snac.feature.tv.season

import com.keetr.snac.core.model.SeasonWithEpisodes

internal sealed interface SeasonScreenUiState {
    data class Success(val season: SeasonWithEpisodes): SeasonScreenUiState
    object Loading: SeasonScreenUiState
    data class Error(val message: String): SeasonScreenUiState

}