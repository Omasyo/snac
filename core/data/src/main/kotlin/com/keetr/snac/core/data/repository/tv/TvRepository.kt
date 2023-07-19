package com.keetr.snac.core.data.repository.tv

import androidx.paging.PagingData
import com.keetr.snac.core.data.TimeWindow
import com.keetr.snac.core.model.EpisodeDetails
import com.keetr.snac.core.model.SeasonWithEpisodes
import com.keetr.snac.core.model.Show
import com.keetr.snac.core.model.Tv
import kotlinx.coroutines.flow.Flow


interface TvRepository {
    suspend fun getDetails(id: Int, language: String = ""): Result<Tv>

    suspend fun getTrending(
        page: Int,
        language: String = "",
        timeWindow: TimeWindow = TimeWindow.Day
    ): Result<List<Show>>

    fun getTrendingStream(
        language: String = "",
        timeWindow: TimeWindow = TimeWindow.Day
    ): Flow<PagingData<Show>>

    suspend fun getAiringToday(
        page: Int,
        language: String = "",
        timezone: String = ""
    ): Result<List<Show>>

    fun getAiringTodayStream(
        language: String = "",
        timezone: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getOnTheAir(
        page: Int,
        language: String = "",
        timezone: String = ""
    ): Result<List<Show>>

    fun getOnTheAirStream(
        language: String = "",
        timezone: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getPopular(
        page: Int,
        language: String = "",
    ): Result<List<Show>>

    fun getPopularStream(
        language: String = "",
    ): Flow<PagingData<Show>>

    suspend fun getTopRated(
        page: Int,
        language: String = "",
    ): Result<List<Show>>

    fun getTopRatedStream(
        language: String = "",
    ): Flow<PagingData<Show>>

    suspend fun getSeasonDetails(
        id: Int,
        seasonNumber: Int,
        language: String = ""
    ): Result<SeasonWithEpisodes>

    suspend fun getEpisodeDetails(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        language: String = ""
    ): Result<EpisodeDetails>
}
