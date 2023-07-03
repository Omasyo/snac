package com.quitr.snac.feature.tv

import com.quitr.snac.core.model.Tv

interface TvScreenUiState {
    data class Success(val tv: Tv) : TvScreenUiState
    object Loading: TvScreenUiState
    data class Error(val message: String) : TvScreenUiState
}