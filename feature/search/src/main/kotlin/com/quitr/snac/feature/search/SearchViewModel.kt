package com.quitr.snac.feature.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quitr.snac.core.data.repository.people.PeopleRepository
import com.quitr.snac.core.data.repository.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchRepository: SearchRepository,
) : ViewModel() {

    private var temp = mutableStateOf("")

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    private val _active = mutableStateOf(false)
    val active: State<Boolean> = _active

    val searchResults get()  = searchRepository.searchAllStream(temp.value)

    private var searchJob: Job? = null


    fun updateQuery(newQuery: String) {
        _query.value = newQuery

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(2000)
            temp.value = newQuery
        }
    }

    fun updateActiveStatus(newStatus: Boolean) {
        _active.value = newStatus
    }

     fun searchAll() =
        searchRepository.searchAllStream(query.value)


}