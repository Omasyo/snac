package com.quitr.snac.feature.discover

import com.quitr.snac.core.model.Show

sealed interface SectionScreenUiState {
    object Loading : SectionScreenUiState
    object Error : SectionScreenUiState
    data class Success(val shows: List<Show>, val page: Int, private val state: PagingState) :
        SectionScreenUiState {
        val isLoading get() = state == PagingState.Loading
        val isIdle get() = state == PagingState.Idle

        operator fun plus(shows: List<Show>) =
            Success(this.shows + shows, page + 1, PagingState.Idle)
    }

    enum class PagingState { Idle, Loading }
}