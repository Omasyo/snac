package com.quitr.snac.core.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.list.TvApiModel
import com.quitr.snac.core.network.tv.TvNetworkDataSource
import com.quitr.snac.core.network.tv.list.TvListApiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


private const val TAG = "TvRepository"

//fun getTvRepository(): TvRepository = DefaultTvRepository(getTvNetworkResource())

interface TvRepository {
    suspend fun getTrending(
        page: Int,
        language: String = "",
        timeWindow: TimeWindow = TimeWindow.Day
    ): Response<List<Show>>

    fun getTrendingStream(
        language: String = "",
        timeWindow: TimeWindow = TimeWindow.Day
    ): Flow<PagingData<Show>>

    suspend fun getAiringToday(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    fun getAiringTodayStream(
        language: String = "",
        region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getOnTheAir(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    fun getOnTheAirStream(
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
}

 class DefaultTvRepository @Inject constructor(
    private val networkDataSource: TvNetworkDataSource
) : TvRepository {
    override suspend fun getTrending(
        page: Int,
        language: String,
        timeWindow: TimeWindow,
    ): Response<List<Show>> = try {
        val results = networkDataSource.getTrending(page, timeWindow.text, language).results
        Success(results.map { tv -> tv.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTrending: $exception")
        Error
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
    ): Response<List<Show>> = try {
        val results = networkDataSource.getAiringToday(page, language, region).results
        Success(results.map { tv -> tv.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getAiringToday: $exception")
        Error
    }

    override fun getAiringTodayStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getAiringToday(page, language, region) }

    override suspend fun getOnTheAir(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getOnTheAir(page, language, region).results
        Success(results.map { tv -> tv.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getOnTheAir: $exception")
        Error
    }

    override fun getOnTheAirStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getOnTheAir(page, language, region) }

    override suspend fun getPopular(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getPopular(page, language, region).results
        Success(results.map { tv -> tv.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getPopular: $exception")
        Error
    }

    override fun getPopularStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getPopular(page, language, region) }

    override suspend fun getTopRated(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getTopRated(page, language, region).results
        Success(results.map { tv -> tv.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTopRated: $exception")
        Error
    }

    override fun getTopRatedStream(language: String, region: String): Flow<PagingData<Show>> =
        getStream { page -> networkDataSource.getTopRated(page, language, region) }

    private fun getStream(provider: suspend (page: Int) -> TvListApiModel): Flow<PagingData<Show>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, enablePlaceholders = false
            )
        ) {
            ShowPagingSource(provider)
        }.flow
    }
}

internal fun TvApiModel.toShow() =
    Show(id, name, voteAverage.toString(), Api.BasePosterPath + posterPath, ShowType.Movie)