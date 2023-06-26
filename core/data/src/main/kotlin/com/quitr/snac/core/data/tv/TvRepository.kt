package com.quitr.snac.core.data.tv

import androidx.paging.PagingData
import com.quitr.snac.core.data.Response
import com.quitr.snac.core.data.TimeWindow
import com.quitr.snac.core.model.Show
import kotlinx.coroutines.flow.Flow


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
