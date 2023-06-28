package com.quitr.snac.feature.movie

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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


    private val _movieDetailsUistate =
        MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetailsUiState: StateFlow<MovieDetailsUiState> = _movieDetailsUistate


    init {
        viewModelScope.launch {
            _movieDetailsUistate.value =
                movieRepository.getDetails(id).getOrElse(MovieDetailsUiState.Error) {
                    MovieDetailsUiState.Success(it)
                }
        }
    }
}