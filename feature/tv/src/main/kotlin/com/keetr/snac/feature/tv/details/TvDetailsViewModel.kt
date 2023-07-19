package com.keetr.snac.feature.tv.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keetr.snac.core.data.repository.tv.TvRepository
import com.keetr.snac.feature.tv.TvIdArg
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TvDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    tvRepository: TvRepository
) : ViewModel() {
    val id = checkNotNull(savedStateHandle.get<Int>(TvIdArg))

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