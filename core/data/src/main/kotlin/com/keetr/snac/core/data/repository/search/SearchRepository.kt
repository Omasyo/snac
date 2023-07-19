package com.keetr.snac.core.data.repository.search

import androidx.paging.PagingData
import com.keetr.snac.core.model.PersonDetails
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchAll(
        query: String,
        page: Int,
        language: String = "",
        adult: Boolean = false
    ) : Result<List<Any>>

    fun searchAllStream(
        query: String,
        language: String = "",
        adult: Boolean = false
    ) : Flow<PagingData<Any>>
}