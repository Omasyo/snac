package com.keetr.snac.feature.movie

import com.keetr.snac.core.model.Movie
internal sealed interface MovieDetailsUiState {
    data class Success(val movie: Movie): MovieDetailsUiState
    object Loading: MovieDetailsUiState
    data class Error(val error: Throwable): MovieDetailsUiState
}