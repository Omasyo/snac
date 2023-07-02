package com.quitr.snac.core.network.tv


import com.quitr.snac.core.network.createClient
import com.quitr.snac.core.network.tv.models.TvDetailsApiModel
import com.quitr.snac.core.network.tv.models.TvListApiModel
import javax.inject.Inject

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class DefaultTvNetworkDataSource @Inject constructor(private val client: HttpClient) :
    TvNetworkDataSource {
    override suspend fun getDetails(id: Int, language: String): TvDetailsApiModel =
        client.get("/3/tv/$id") {
            parameter("language", language)
            parameter("append_to_response", "aggregate_credits,keywords,recommendations,similar")
        }.body()

    override suspend fun getTrending(
        page: Int,
        timeWindow: String,
        language: String
    ): TvListApiModel = client.get("/3/trending/tv/$timeWindow") {
        parameter("page", page.toString())
        parameter("language", language)
    }.body()

    override suspend fun getAiringToday(page: Int, language: String, timezone: String) =
        getTvList("airing_today", page, language, timezone)

    override suspend fun getOnTheAir(page: Int, language: String, timezone: String) =
        getTvList("on_the_air", page, language, timezone)

    override suspend fun getPopular(page: Int, language: String, timezone: String) =
        getTvList("popular", page, language, timezone)

    override suspend fun getTopRated(page: Int, language: String, timezone: String) =
        getTvList("top_rated", page, language, timezone)

    private suspend inline fun getTvList(
        path: String,
        page: Int,
        language: String,
        timezone: String
    ): TvListApiModel =
        client.get("/3/tv/$path") {
            parameter("page", page.toString())
            parameter("language", language)
        }.body()
}