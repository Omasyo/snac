package com.quitr.snac.core.ui.section

import com.quitr.snac.core.model.Show

sealed interface ShowCarouselUiState {
    data class Success(val shows: List<Show>): ShowCarouselUiState
    object Loading: ShowCarouselUiState
    object Error: ShowCarouselUiState
}