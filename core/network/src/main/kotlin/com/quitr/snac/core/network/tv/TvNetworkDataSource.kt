package com.quitr.snac.core.network.tv

import com.quitr.snac.core.network.tv.list.TvListApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject


interface TvNetworkDataSource {

    suspend fun getTrending(page: Int, timeWindow: String, language: String): TvListApiModel

    suspend fun getAiringToday(page: Int, language: String, timezone: String): TvListApiModel

    suspend fun getOnTheAir(page: Int, language: String, timezone: String): TvListApiModel

    suspend fun getPopular(page: Int, language: String, timezone: String): TvListApiModel

    suspend fun getTopRated(page: Int, language: String, timezone: String): TvListApiModel
}
