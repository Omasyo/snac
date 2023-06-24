package com.quitr.snac.core.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.network.movie.MovieNetworkDataSource
import com.quitr.snac.core.network.movie.list.MovieListApiModel
import com.quitr.snac.core.network.tv.TvNetworkDataSource
import com.quitr.snac.core.network.tv.list.TvApiModel
import com.quitr.snac.core.network.tv.list.TvListApiModel

class ShowPagingSource<T>(
    val getAiringToday: suspend (Int, String, String) -> T,
) : PagingSource<Int, Show>() {
    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, Show> {
//        val getAiringToday: (Int, String, String) -> TvListApiModel = { a, b, c ->
//            TvListApiModel(1, listOf<TvApiModel>(), 2, 3)
//        }
        return try {
            val page = params.key ?: 1
            val response = when(val listApiModel = getAiringToday(page, "","")) {
                is TvListApiModel -> listApiModel.results.map { it.toShow() }
                is MovieListApiModel -> listApiModel.results.map { it.toShow() }
                else -> throw Exception("Invalid Type. Should be TvListApiModel or MovieListApiModel")
            }
            PagingSource.LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)
        }
    }
}