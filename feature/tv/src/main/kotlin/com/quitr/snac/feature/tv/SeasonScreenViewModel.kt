package com.quitr.snac.feature.tv

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quitr.snac.core.data.repository.tv.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    tvRepository: TvRepository
) : ViewModel() {
    private val id = checkNotNull(savedStateHandle.get<Int>(tvId))
    private val seasonNumber = checkNotNull(savedStateHandle.get<Int>(seasonNumberArg))

    private val _seasonScreenUiState =
        MutableStateFlow<SeasonScreenUiState>(SeasonScreenUiState.Loading)
    val seasonScreenUiState: StateFlow<SeasonScreenUiState> = _seasonScreenUiState

    init {
        viewModelScope.launch {
            _seasonScreenUiState.value = tvRepository.getSeasonDetails(id, seasonNumber)
                .fold({ SeasonScreenUiState.Success(it) }) {
                    SeasonScreenUiState.Error(it.message ?: "")
                }
        }
    }
}