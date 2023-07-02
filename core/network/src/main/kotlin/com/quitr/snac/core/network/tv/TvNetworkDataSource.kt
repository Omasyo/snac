package com.quitr.snac.core.network.tv

import com.quitr.snac.core.network.tv.models.TvDetailsApiModel
import com.quitr.snac.core.network.tv.models.TvListApiModel


interface TvNetworkDataSource {
    suspend fun getDetails(id: Int, language: String): TvDetailsApiModel
    suspend fun getTrending(page: Int, timeWindow: String, language: String): TvListApiModel

    suspend fun getAiringToday(page: Int, language: String, timezone: String): TvListApiModel

    suspend fun getOnTheAir(page: Int, language: String, timezone: String): TvListApiModel

    suspend fun getPopular(page: Int, language: String, timezone: String): TvListApiModel

    suspend fun getTopRated(page: Int, language: String, timezone: String): TvListApiModel
}
