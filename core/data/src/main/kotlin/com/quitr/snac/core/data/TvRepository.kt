package com.quitr.snac.core.data

import android.util.Log
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.Api
import com.quitr.snac.core.network.tv.list.TvApiModel
import com.quitr.snac.core.network.tv.TvNetworkDataSource
import com.quitr.snac.core.network.tv.getTvNetworkResource


private const val TAG = "TvRepository"

fun getTvRepository(): TvRepository = DefaultTvRepository(getTvNetworkResource())

interface TvRepository {
    suspend fun getTrending(
        page: Int,
        language: String = "",
        timeWindow: TimeWindow = TimeWindow.Day
    ): Response<List<Show>>

    suspend fun getAiringToday(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    suspend fun getOnTheAir(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    suspend fun getPopular(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    suspend fun getTopRated(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>
}

private class DefaultTvRepository(
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

}

private fun TvApiModel.toShow() =
    Show(id, name, voteAverage.toString(), Api.BasePosterPath + posterPath, ShowType.Movie)