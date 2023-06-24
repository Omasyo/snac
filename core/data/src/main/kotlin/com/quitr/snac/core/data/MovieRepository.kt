package com.quitr.snac.core.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.movie.MovieNetworkDataSource
import com.quitr.snac.core.network.movie.getMovieNetworkDataSource
import com.quitr.snac.core.network.movie.list.MovieApiModel
import com.quitr.snac.core.network.movie.list.MovieListApiModel
import com.quitr.snac.core.network.tv.list.TvListApiModel
import kotlinx.coroutines.flow.Flow


private const val TAG = "MovieRepository"
fun getMovieRepository(
//    private val localDataSource: MovieLocalDataSource
): MovieRepository = DefaultMovieRepository(getMovieNetworkDataSource())

enum class TimeWindow(val text: String) { Day("day"), Week("week") }

interface MovieRepository {

    suspend fun getTrending(
        page: Int,
        language: String = "",
        timeWindow: TimeWindow = TimeWindow.Day
    ): Response<List<Show>>

    fun getTrendingStream(
language: String = "",
        timeWindow: TimeWindow = TimeWindow.Day
    ): Flow<PagingData<Show>>

    suspend fun getNowPlaying(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    fun getNowPlayingStream(
language: String = "",
        region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getPopular(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    fun getPopularStream(
language: String = "",
        region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getTopRated(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    fun getTopRatedStream(
language: String = "",
        region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getUpcoming(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    fun getUpcomingStream(
language: String = "",
        region: String = ""
    ): Flow<PagingData<Show>>
}

private class DefaultMovieRepository(
    private val networkDataSource: MovieNetworkDataSource,
//    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun getTrending(
        page: Int,
        language: String,
        timeWindow: TimeWindow
    ): Response<List<Show>> = try {
        val results = networkDataSource.getTrending(page, timeWindow.text, language).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTrending: $exception")
        Error
    }

    override fun getTrendingStream(language: String, timeWindow: TimeWindow): Flow<PagingData<Show>> =
    getStream(networkDataSource::getNowPlaying)

    override suspend fun getNowPlaying(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getNowPlaying(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getNowPlaying: $exception")
        Error
    }

    override fun getNowPlayingStream(language: String, region: String): Flow<PagingData<Show>> =
    getStream(networkDataSource::getNowPlaying)

    override suspend fun getPopular(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getPopular(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getPopular: $exception")
        Error
    }

    override fun getPopularStream(language: String, region: String): Flow<PagingData<Show>>  =
        getStream(networkDataSource::getPopular)

    override suspend fun getTopRated(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getTopRated(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTopRated: $exception")
        Error
    }

    override fun getTopRatedStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream(networkDataSource::getTopRated)

    override suspend fun getUpcoming(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getUpcoming(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getUpcoming: $exception")
        Error
    }

    override fun getUpcomingStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream(networkDataSource::getUpcoming)

    private fun getStream(func: suspend (Int, String, String) -> MovieListApiModel): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ShowPagingSource(func) }
        ).flow
    }
}

internal fun MovieApiModel.toShow() =
    Show(id, title, voteAverage.toString(), Api.BasePosterPath + remove + posterPath, ShowType.Movie)