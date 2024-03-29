package com.keetr.snac.feature.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keetr.snac.core.data.repository.movie.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val id = checkNotNull(savedStateHandle.get<Int>(MovieIdArg))

    private val _movieDetailsUiState =
        MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetailsUiState: StateFlow<MovieDetailsUiState> = _movieDetailsUiState

    fun refresh(){
        _movieDetailsUiState.value = MovieDetailsUiState.Loading
        viewModelScope.launch {
            _movieDetailsUiState.value =
                movieRepository.getDetails(id).fold({ MovieDetailsUiState.Success(it) }) {
                    MovieDetailsUiState.Error(it)
                }
        }
    }

    init {
        refresh()
    }
}