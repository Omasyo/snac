package com.keetr.snac.core.data.pager.show

import androidx.paging.PagingSource
import androidx.paging.PagingState

class CustomPagingSource<T, R : Any>(
    val provider: suspend (page: Int) -> List<T>,
    val mapper: List<T>.() -> List<R>,
) : PagingSource<Int, R>() {
    override fun getRefreshKey(state: PagingState<Int, R>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, R> {
        return try {
            val page = params.key ?: 1
            val response = provider(page).mapper()
           LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}