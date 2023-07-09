package com.quitr.snac.feature.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.quitr.snac.core.data.repository.people.PeopleRepository
import com.quitr.snac.core.data.repository.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchRepository: SearchRepository,
) : ViewModel() {

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    private val _active = mutableStateOf(false)
    val active: State<Boolean> = _active

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun updateActiveStatus(newStatus: Boolean) {
        _active.value = newStatus
    }

     fun searchAll() =
        searchRepository.searchAllStream(query.value)

}