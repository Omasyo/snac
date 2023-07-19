package com.keetr.snac.core.ui.carousel

import com.keetr.snac.core.model.Show

sealed interface ShowCarouselUiState {
    data class Success(val shows: List<Show>): ShowCarouselUiState
    object Loading: ShowCarouselUiState
    object Error: ShowCarouselUiState
}