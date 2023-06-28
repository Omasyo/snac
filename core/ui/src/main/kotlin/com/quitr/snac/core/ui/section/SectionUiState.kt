package com.quitr.snac.core.ui.section

import com.quitr.snac.core.model.Show

sealed interface SectionUiState {
    data class Success(val shows: List<Show>): SectionUiState
    object Loading: SectionUiState
    object Error: SectionUiState
}