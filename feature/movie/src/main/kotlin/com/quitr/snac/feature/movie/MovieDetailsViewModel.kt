package com.quitr.snac.feature.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quitr.snac.core.data.getOrElse
import com.quitr.snac.core.data.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    movieRepository: MovieRepository,
) : ViewModel() {
    private val id = savedStateHandle.get<Int>("movieId")!!


    private val _movieDetailsUiState =
        MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetailsUiState: StateFlow<MovieDetailsUiState> = _movieDetailsUiState


    init {
        viewModelScope.launch {
            _movieDetailsUiState.value =
                movieRepository.getDetails(id).getOrElse(MovieDetailsUiState.Error) {
                    MovieDetailsUiState.Success(it)
                }
        }
    }
}