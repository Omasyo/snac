package com.quitr.snac.core.network

import com.quitr.snac.core.network.movielist.MovieListApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.parameters

fun getMovieNetworkDataSource(): MovieNetworkDataSource =
    DefaultMovieNetworkDataSource(Client)

interface MovieNetworkDataSource {
//    suspend fun getDetails(id: Int, language: String)

    suspend fun getTrending(page: Int, timeWindow: String, language: String): MovieListApiModel

    suspend fun getNowPlaying(page: Int, language: String, region: String): MovieListApiModel

    suspend fun getPopular(page: Int, language: String, region: String): MovieListApiModel

    suspend fun getTopRated(page: Int, language: String, region: String): MovieListApiModel

    suspend fun getUpcoming(page: Int, language: String, region: String): MovieListApiModel
}

private class DefaultMovieNetworkDataSource(private val client: HttpClient) :
    MovieNetworkDataSource {
    override suspend fun getTrending(
        page: Int,
        timeWindow: String,
        language: String
    ): MovieListApiModel =
        client.get("/3/trending/movie/$timeWindow") {
            parameters {
                append("page", page.toString())
                append("language", language)
            }
        }.body()

    override suspend fun getNowPlaying(
        page: Int,
        language: String,
        region: String
    ): MovieListApiModel = getMovieList("now_playing", page, language, region)


    override suspend fun getPopular(
        page: Int,
        language: String,
        region: String
    ): MovieListApiModel = getMovieList("popular", page, language, region)

    override suspend fun getTopRated(
        page: Int,
        language: String,
        region: String
    ): MovieListApiModel = getMovieList("top_rated", page, language, region)

    override suspend fun getUpcoming(
        page: Int,
        language: String,
        region: String
    ): MovieListApiModel = getMovieList("upcoming", page, language, region)

    private suspend inline fun getMovieList(
        path: String,
        page: Int,
        language: String,
        region: String
    ): MovieListApiModel =
        client.get("/3/movie/$path") {
            parameters {
                append("page", page.toString())
                append("language", language)
                append("region", region)
            }
        }.body()
}