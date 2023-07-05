
package com.quitr.snac.core.data.repository.movie

import androidx.paging.PagingData
import com.quitr.snac.core.data.TimeWindow
import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Show
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getDetails(id: Int, language: String = ""): Result<Movie>


    suspend fun getTrending(
        page: Int, language: String = "", timeWindow: TimeWindow = TimeWindow.Day
    ): Result<List<Show>>

    fun getTrendingStream(
        language: String = "", timeWindow: TimeWindow = TimeWindow.Day
    ): Flow<PagingData<Show>>

    suspend fun getNowPlaying(
        page: Int, language: String = "", region: String = ""
    ): Result<List<Show>>

    fun getNowPlayingStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getPopular(
        page: Int, language: String = "", region: String = ""
    ): Result<List<Show>>

    fun getPopularStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getTopRated(
        page: Int, language: String = "", region: String = ""
    ): Result<List<Show>>

    fun getTopRatedStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getUpcoming(
        page: Int, language: String = "", region: String = ""
    ): Result<List<Show>>

    fun getUpcomingStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

//     fun getRecommendationStream(id: Int,  language: String = "") : Flow<PagingData<Show>>
//
//    suspend fun getSimilarStream(id: Int, language: String = "") : Flow<PagingData<Show>>
}