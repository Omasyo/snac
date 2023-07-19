package com.keetr.snac.feature.tv.episode

import com.keetr.snac.core.model.EpisodeDetails

internal sealed interface EpisodeDetailScreenUiState {
    data class Success(val episode: EpisodeDetails): EpisodeDetailScreenUiState
    object Loading: EpisodeDetailScreenUiState
    data class Error(val message: String): EpisodeDetailScreenUiState
}