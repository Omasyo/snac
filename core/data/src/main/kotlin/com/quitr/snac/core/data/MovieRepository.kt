package com.quitr.snac.core.data

import androidx.paging.PagingData
import com.quitr.snac.core.model.Show
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getTrending(
        page: Int, language: String = "", timeWindow: TimeWindow = TimeWindow.Day
    ): Response<List<Show>>

    fun getTrendingStream(
        language: String = "", timeWindow: TimeWindow = TimeWindow.Day
    ): Flow<PagingData<Show>>

    suspend fun getNowPlaying(
        page: Int, language: String = "", region: String = ""
    ): Response<List<Show>>

    fun getNowPlayingStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getPopular(
        page: Int, language: String = "", region: String = ""
    ): Response<List<Show>>

    fun getPopularStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getTopRated(
        page: Int, language: String = "", region: String = ""
    ): Response<List<Show>>

    fun getTopRatedStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getUpcoming(
        page: Int, language: String = "", region: String = ""
    ): Response<List<Show>>

    fun getUpcomingStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>
}