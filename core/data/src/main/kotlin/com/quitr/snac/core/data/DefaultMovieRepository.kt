package com.quitr.snac.core.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.movie.MovieNetworkDataSource
import com.quitr.snac.core.network.movie.list.MovieApiModel
import com.quitr.snac.core.network.movie.list.MovieListApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "DefaultMovieRepository"

internal class DefaultMovieRepository @Inject constructor(
    private val networkDataSource: MovieNetworkDataSource,
//    private val localDataSource: MovieLocalDataSource
    @Named("IO") private val dispatcher: CoroutineDispatcher,
) : MovieRepository {
    override suspend fun getTrending(
        page: Int, language: String, timeWindow: TimeWindow
    ): Response<List<Show>> = withContext(dispatcher) {
        try {
            val results = networkDataSource.getTrending(page, timeWindow.text, language).results
            Success(results.map { movie -> movie.toShow() })
        } catch (exception: Exception) {
            Log.d(TAG, "getTrending: $exception")
            Error
        }
    }

    override fun getTrendingStream(
        language: String, timeWindow: TimeWindow
    ): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getTrending(page, timeWindow.text, language) }

    override suspend fun getNowPlaying(
        page: Int, language: String, region: String
    ): Response<List<Show>> = getList(page, language, region, networkDataSource::getNowPlaying)

    override fun getNowPlayingStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getNowPlaying(page, language, region) }

    override suspend fun getPopular(
        page: Int, language: String, region: String
    ): Response<List<Show>> = getList(page, language, region, networkDataSource::getPopular)

    override fun getPopularStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getPopular(page, language, region) }

    override suspend fun getTopRated(
        page: Int, language: String, region: String
    ): Response<List<Show>> = getList(page, language, region, networkDataSource::getTopRated)

    override fun getTopRatedStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getTopRated(page, language, region) }

    override suspend fun getUpcoming(
        page: Int, language: String, region: String
    ): Response<List<Show>> = getList(page, language, region, networkDataSource::getUpcoming)

    override fun getUpcomingStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getUpcoming(page, language, region) }

    private suspend fun getList(
        page: Int,
        language: String,
        region: String,
        func: suspend (page: Int, language: String, region: String) -> MovieListApiModel
    ) = withContext(dispatcher) {
        try {
            val results = func(page, language, region).results
            Success(results.map { movie -> movie.toShow() })
        } catch (exception: Exception) {
            Log.d(TAG, "getList: $exception")
            Error
        }
    }

    private fun getStream(provider: suspend (page: Int) -> MovieListApiModel): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, enablePlaceholders = false
            )
        ) {
            println("HROT New pager")
            ShowPagingSource(provider)
        }.flow.flowOn(dispatcher)
    }
}

internal fun MovieApiModel.toShow() = Show(
    id, title, voteAverage.toString(), Api.BasePosterPath + posterPath, ShowType.Movie
)