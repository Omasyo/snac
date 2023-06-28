package com.quitr.snac.feature.movie

import com.quitr.snac.core.model.Movie
sealed interface MovieDetailsUiState {
    data class Success(val movie: Movie): MovieDetailsUiState
    object Loading: MovieDetailsUiState
    object Error: MovieDetailsUiState
}