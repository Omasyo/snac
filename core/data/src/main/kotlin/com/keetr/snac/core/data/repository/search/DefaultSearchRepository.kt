package com.keetr.snac.core.data.repository.search

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.keetr.snac.core.data.mapppers.toSearchResults
import com.keetr.snac.core.data.pager.show.CustomPagingSource
import com.keetr.snac.core.network.search.SearchNetworkDataSource
import com.keetr.snac.core.network.search.models.SearchResultApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "DefaultSearchRepository"

class DefaultSearchRepository @Inject constructor(
    private val networkDataSource: SearchNetworkDataSource,
    @Named("IO") private val dispatcher: CoroutineDispatcher,
) : SearchRepository {
    override suspend fun searchAll(
        query: String,
        page: Int,
        language: String,
        adult: Boolean
    ): Result<List<Any>> = withContext(dispatcher) {
        try {
            val result =
                networkDataSource.searchAll(query, page, language, adult).results.toSearchResults()
            Result.success(result)
        } catch (exception: Exception) {
            Log.d(TAG, "searchAll: $exception")
            Result.failure(exception)
        }
    }

    override fun searchAllStream(
        query: String,
        language: String,
        adult: Boolean
    ): Flow<PagingData<Any>> = Pager(
        config = PagingConfig(
            pageSize = 20, enablePlaceholders = false
        )
    ) {
        CustomPagingSource(
            provider = { page ->
                networkDataSource.searchAll(
                    query,
                    page,
                    language,
                    adult
                ).results
            },
            mapper = List<SearchResultApiModel>::toSearchResults
        )
    }.flow.flowOn(dispatcher)
}