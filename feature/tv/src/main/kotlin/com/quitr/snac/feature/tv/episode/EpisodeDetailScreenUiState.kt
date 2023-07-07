package com.quitr.snac.feature.tv.episode

import com.quitr.snac.core.model.EpisodeDetails

internal sealed interface EpisodeDetailScreenUiState {
    data class Success(val episode: EpisodeDetails): EpisodeDetailScreenUiState
    object Loading: EpisodeDetailScreenUiState
    data class Error(val message: String): EpisodeDetailScreenUiState
}