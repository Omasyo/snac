package com.keetr.snac.feature.people

import com.keetr.snac.core.model.PersonDetails

internal sealed interface PersonDetailsUiState {
    data class Success(val person: PersonDetails): PersonDetailsUiState
    object Loading: PersonDetailsUiState
    data class Error(val error: Throwable): PersonDetailsUiState
}