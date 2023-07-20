package com.keetr.snac.feature.search

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.keetr.snac.core.data.repository.people.PeopleRepository
import com.keetr.snac.core.data.repository.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val searchRepository: SearchRepository,
) : ViewModel() {

    private var temp = MutableStateFlow("")

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    private val _active = mutableStateOf(false)
    val active: State<Boolean> = _active

    @OptIn(ExperimentalCoroutinesApi::class)
    val searchResults  = temp.flatMapLatest { newQuery -> searchRepository.searchAllStream(newQuery).cachedIn(viewModelScope) }

    private var searchJob: Job? = null


    fun updateQuery(newQuery: String) {
        _query.value = newQuery

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            temp.value = newQuery
        }
    }

    fun clearQuery() = updateQuery("")

    fun updateActiveStatus(newStatus: Boolean) {
        _active.value = newStatus
        if(!newStatus) {
            clearQuery()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun refresh() {
//        temp.resetReplayCache()
//        temp.value = _query.value
    }

     fun searchAll() =
        searchRepository.searchAllStream(query.value)


}