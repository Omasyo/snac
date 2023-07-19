package com.keetr.snac.feature.tv.details

import com.keetr.snac.core.model.Tv

internal sealed interface TvScreenUiState {
    data class Success(val tv: Tv) : TvScreenUiState
    object Loading: TvScreenUiState
    data class Error(val message: String) : TvScreenUiState
}