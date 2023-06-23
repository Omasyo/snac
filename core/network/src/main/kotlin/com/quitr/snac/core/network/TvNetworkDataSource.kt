package com.quitr.snac.core.network

import com.quitr.snac.core.network.movielist.MovieListApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters
import java.util.TimeZone

fun getTvNetworkResource(): TvNetworkDataSource =
    DefaultTvNetworkDataSource(Client)

interface TvNetworkDataSource {

    suspend fun getTrending(page: Int, timeWindow: String, language: String): TvListApiModel

    suspend fun getAiringToday(page: Int, language: String, timezone: String): TvListApiModel

    suspend fun getOnTheAir(page: Int, language: String, timezone: String): TvListApiModel

    suspend fun getPopular(page: Int, language: String, timezone: String): TvListApiModel

    suspend fun getTopRated(page: Int, language: String, timezone: String): TvListApiModel
}

class DefaultTvNetworkDataSource(private val client: HttpClient) : TvNetworkDataSource {
    override suspend fun getTrending(
        page: Int,
        timeWindow: String,
        language: String
    ): TvListApiModel = client.get("/3/trending/tv/$timeWindow") {
        parameters {
            append("page", page.toString())
            append("language", language)
        }
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
            parameters {
                append("page", page.toString())
                append("language", language)
                append("timezone", timezone)
            }
        }.body()
}