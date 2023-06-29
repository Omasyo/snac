package com.quitr.snac.core.data.movie

import androidx.paging.PagingData
import com.quitr.snac.core.data.Response
import com.quitr.snac.core.data.TimeWindow
import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Person
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.network.movie.list.MovieApiModel
import com.quitr.snac.core.network.movie.models.CrewApiModel
import com.quitr.snac.core.network.movie.models.ProductionCompanyApiModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getDetails(id: Int, language: String = ""): Response<Movie>


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

     fun getRecommendationStream(id: Int,  language: String) : Flow<PagingData<Show>>

    suspend fun getSimilarStream(id: Int, language: String) : Flow<PagingData<Show>>
}