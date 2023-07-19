package com.keetr.snac.feature.people

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keetr.snac.core.data.repository.people.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PersonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    peopleRepository: PeopleRepository,
) : ViewModel() {
    private val id = checkNotNull(savedStateHandle.get<Int>(PersonIdArg))


    private val _personDetailsUiState =
        MutableStateFlow<PersonDetailsUiState>(PersonDetailsUiState.Loading)
    val personDetailsUiState: StateFlow<PersonDetailsUiState> = _personDetailsUiState

    init {
        viewModelScope.launch {
            _personDetailsUiState.value =
                peopleRepository.getDetails(id).fold({ PersonDetailsUiState.Success(it) }) {
                    PersonDetailsUiState.Error(it)
                }
        }
    }
}