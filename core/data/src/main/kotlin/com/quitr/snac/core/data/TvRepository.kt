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
