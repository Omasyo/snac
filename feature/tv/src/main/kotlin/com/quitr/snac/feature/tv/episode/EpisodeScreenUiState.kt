package com.quitr.snac.feature.tv.episode

import com.quitr.snac.core.model.EpisodeDetails

internal sealed interface EpisodeScreenUiState {
    data class Success(val episode: EpisodeDetails): EpisodeScreenUiState
    object Loading: EpisodeScreenUiState
    data class Error(val message: String): EpisodeScreenUiState
}