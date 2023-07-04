package com.quitr.snac.core.data.repository.tv

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.quitr.snac.core.data.TimeWindow
import com.quitr.snac.core.data.mapppers.toShow
import com.quitr.snac.core.data.mapppers.toShows
import com.quitr.snac.core.data.mapppers.toTv
import com.quitr.snac.core.data.pager.show.ShowPagingSource
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.model.Tv
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.TvNetworkDataSource
import com.quitr.snac.core.network.tv.models.TvApiModel
import com.quitr.snac.core.network.tv.models.TvListApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

private const val TAG = "DefaultTvRepository"

internal class DefaultTvRepository @Inject constructor(
    private val networkDataSource: TvNetworkDataSource,
    @Named("IO") private val dispatcher: CoroutineDispatcher,
) : TvRepository {
    override suspend fun getDetails(id: Int, language: String): Result<Tv> = withContext(dispatcher) {
        try {
            val result = networkDataSource.getDetails(id, language)
            Result.success(result.toTv())
        } catch (exception: Exception) {
            Log.d(TAG, "getDetails: $exception")
            Result.failure(exception)
        }
    }

    override suspend fun getTrending(
        page: Int,
        language: String,
        timeWindow: TimeWindow,
    ): Result<List<Show>> = withContext(dispatcher) {
        try {
            val results = networkDataSource.getTrending(page, timeWindow.text, language).results
            Result.success(results.toShows())
        } catch (exception: Exception) {
            Log.d(TAG, "getTrending: $exception")
            Result.failure(exception)
        }
    }

    override fun getTrendingStream(
        language: String,
        timeWindow: TimeWindow
    ): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getTrending(page, timeWindow.text, language) }


    override suspend fun getAiringToday(
        page: Int,
        language: String,
        region: String
    ): Result<List<Show>> = getList(page, language, region, networkDataSource::getAiringToday)

    override fun getAiringTodayStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getAiringToday(page, language, region) }

    override suspend fun getOnTheAir(
        page: Int,
        language: String,
        region: String
    ): Result<List<Show>> = getList(page, language, region, networkDataSource::getOnTheAir)

    override fun getOnTheAirStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getOnTheAir(page, language, region) }

    override suspend fun getPopular(
        page: Int,
        language: String,
        region: String
    ): Result<List<Show>> = getList(page, language, region, networkDataSource::getPopular)

    override fun getPopularStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getPopular(page, language, region) }

    override suspend fun getTopRated(
        page: Int,
        language: String,
        region: String
    ): Result<List<Show>> = getList(page, language, region, networkDataSource::getTopRated)

    override fun getTopRatedStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getTopRated(page, language, region) }

    private suspend fun getList(
        page: Int,
        language: String,
        region: String,
        func: suspend (page: Int, language: String, region: String) -> TvListApiModel
    ) = withContext(dispatcher) {
        try {
            val results = func(page, language, region).results
            Result.success(results.toShows())
        } catch (exception: Exception) {
            Log.d(TAG, "getList: $exception")
            Result.failure(exception)
        }
    }

    private fun getStream(provider: suspend (page: Int) -> TvListApiModel): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, enablePlaceholders = false
            )
        ) {
            ShowPagingSource(
                provider = { provider(it).results },
                mapper = List<TvApiModel>::toShows
            )
        }.flow.flowOn(dispatcher)
    }
}