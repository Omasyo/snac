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
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val TAG = "DefaultMovieRepository"

internal class DefaultMovieRepository @Inject constructor(
    private val networkDataSource: MovieNetworkDataSource,
//    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun getTrending(
        page: Int, language: String, timeWindow: TimeWindow
    ): Response<List<Show>> = try {
        val results = networkDataSource.getTrending(page, timeWindow.text, language).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTrending: $exception")
        Error
    }

    override fun getTrendingStream(
        language: String, timeWindow: TimeWindow
    ): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getTrending(page, timeWindow.text, language) }

    override suspend fun getNowPlaying(
        page: Int, language: String, region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getNowPlaying(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getNowPlaying: $exception")
        Error
    }

    override fun getNowPlayingStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getNowPlaying(page, language, region) }

    override suspend fun getPopular(
        page: Int, language: String, region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getPopular(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getPopular: $exception")
        Error
    }

    override fun getPopularStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getPopular(page, language, region) }

    override suspend fun getTopRated(
        page: Int, language: String, region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getTopRated(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTopRated: $exception")
        Error
    }

    override fun getTopRatedStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getTopRated(page, language, region) }

    override suspend fun getUpcoming(
        page: Int, language: String, region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getUpcoming(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getUpcoming: $exception")
        Error
    }

    override fun getUpcomingStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getUpcoming(page, language, region) }

    private fun getStream(provider: suspend (page: Int) -> MovieListApiModel): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, enablePlaceholders = false
            )
        ) {
            ShowPagingSource(provider)
        }.flow
    }
}

internal fun MovieApiModel.toShow() = Show(
    id, title, voteAverage.toString(), Api.BasePosterPath + posterPath, ShowType.Movie
)