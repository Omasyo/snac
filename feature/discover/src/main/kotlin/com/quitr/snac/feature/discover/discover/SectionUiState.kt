package com.quitr.snac.feature.discover.discover

import com.quitr.snac.core.model.Show

sealed interface SectionUiState {
    data class Success(val shows: List<Show>): SectionUiState
    object Loading: SectionUiState
    object Error: SectionUiState
}