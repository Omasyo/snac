package com.quitr.snac.feature.people

import com.quitr.snac.core.model.PersonDetails

internal sealed interface PersonDetailsUiState {
    data class Success(val person: PersonDetails): PersonDetailsUiState
    object Loading: PersonDetailsUiState
    data class Error(val error: Throwable): PersonDetailsUiState
}