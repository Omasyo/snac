package com.quitr.snac.core.data.show

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.quitr.snac.core.data.movie.toShow
import com.quitr.snac.core.data.tv.toShow
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.network.movie.list.MovieListApiModel
import com.quitr.snac.core.network.tv.list.TvListApiModel

class ShowPagingSource<T>(
//    val getAiringToday: suspend (page: Int, language: String, region: String) -> T,
    val provider: suspend (page: Int) -> T,
) : PagingSource<Int, Show>() {
    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        return try {
            val page = params.key ?: 1
            val response = when(val listApiModel = provider(page)) {
                is TvListApiModel -> listApiModel.results.map { it.toShow() }
                is MovieListApiModel -> listApiModel.results.map { it.toShow() }
                else -> throw Exception("Invalid Type. Should be TvListApiModel or MovieListApiModel")
            }
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