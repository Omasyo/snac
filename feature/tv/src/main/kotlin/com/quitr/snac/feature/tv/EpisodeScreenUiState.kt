package com.quitr.snac.feature.tv

import com.quitr.snac.core.model.EpisodeDetails

interface EpisodeScreenUiState {
    data class Success(val episode: EpisodeDetails): EpisodeScreenUiState
    object Loading: EpisodeScreenUiState
    data class Error(val message: String): EpisodeScreenUiState

}