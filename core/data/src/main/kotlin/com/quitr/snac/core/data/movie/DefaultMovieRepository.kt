package com.quitr.snac.core.data.movie

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.quitr.snac.core.data.Error
import com.quitr.snac.core.data.Response
import com.quitr.snac.core.data.show.ShowPagingSource
import com.quitr.snac.core.data.Success
import com.quitr.snac.core.data.TimeWindow
import com.quitr.snac.core.data.mapppers.toMovie
import com.quitr.snac.core.data.mapppers.toShows
import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.network.movie.MovieNetworkDataSource
import com.quitr.snac.core.network.movie.list.MovieApiModel
import com.quitr.snac.core.network.movie.list.MovieListApiModel
import com.quitr.snac.core.network.movie.models.ProductionCountryApiModel
import com.quitr.snac.core.network.movie.models.RecommendationApiModel
import com.quitr.snac.core.network.movie.models.SpokenLanguageApiModel
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
    override suspend fun getDetails(id: Int, language: String): Response<Movie> =
        withContext(dispatcher) {
            try {
                val result = networkDataSource.getDetails(id, language).toMovie()
                Success(result)
            } catch (exception: Exception) {
                Log.d(TAG, "getDetails: $exception")
                Error
            }
        }

    override suspend fun getTrending(
        page: Int, language: String, timeWindow: TimeWindow
    ): Response<List<Show>> = withContext(dispatcher) {
        try {
            val results =
                networkDataSource.getTrending(page, timeWindow.text, language).results.toShows()
            Success(results)
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
        getStream { page -> networkDataSource.getNowPlaying(page, language, region) }gi

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

    override fun getRecommendationStream(
        id: Int,
        language: String
    ): Flow<PagingData<Show>> = Pager(
        config = PagingConfig(
            pageSize = 20, enablePlaceholders = false
        )
    ) {
        ShowPagingSource(
            provider = { page -> networkDataSource.getRecommendation(id, page, language).results },
            mapper = List<RecommendationApiModel>::toShows
        )
    }.flow.flowOn(dispatcher)

    override suspend fun getSimilarStream(
        id: Int,
        language: String
    ): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getSimilar(id, page, language) }

    private suspend fun getList(
        page: Int,
        language: String,
        region: String,
        func: suspend (page: Int, language: String, region: String) -> MovieListApiModel
    ) = withContext(dispatcher) {
        try {
            val results = func(page, language, region).results.toShows()
            Success(results)
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
            ShowPagingSource(
                provider = { provider(it).results },
                mapper = List<MovieApiModel>::toShows
            )
        }.flow.flowOn(dispatcher)
    }
}