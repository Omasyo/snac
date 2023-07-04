package com.quitr.snac.feature.tv

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quitr.snac.core.data.repository.tv.TvRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TvDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    tvRepository: TvRepository
) : ViewModel() {
    private val id = checkNotNull(savedStateHandle.get<Int>(TvDetailsRoute.tvId))

    private val _tvDetailsUiState =
        MutableStateFlow<TvScreenUiState>(TvScreenUiState.Loading)
    val tvDetailsUiState: StateFlow<TvScreenUiState> = _tvDetailsUiState

    init {
        viewModelScope.launch {
            _tvDetailsUiState.value =
                tvRepository.getDetails(id).fold({ TvScreenUiState.Success(it) }) {
                    TvScreenUiState.Error(it.message ?: "")
                }
        }
    }
}