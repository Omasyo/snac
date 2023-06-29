package com.quitr.snac.core.network.movie

import com.quitr.snac.core.network.movie.list.MovieListApiModel
import com.quitr.snac.core.network.movie.models.MovieDetailsApiModel
import com.quitr.snac.core.network.movie.models.RecommendationsApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

internal class DefaultMovieNetworkDataSource @Inject constructor(private val client: HttpClient) :
    MovieNetworkDataSource {
    override suspend fun getDetails(id: Int, language: String): MovieDetailsApiModel =
        client.get("/3/movie/$id") {
            parameter("language", language)
            parameter("append_to_response", "credits,keywords,recommendations,similar")

        }.body()

    override suspend fun getTrending(
        page: Int,
        timeWindow: String,
        language: String
    ): MovieListApiModel =
        client.get("/3/trending/movie/$timeWindow") {
            parameter("page", page.toString())
            parameter("language", language)
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

    override suspend fun getRecommendation(
        id: Int,
        page: Int,
        language: String
    ): RecommendationsApiModel =
        client.get("/3/movie/$id/recommendations") {
            parameter("page", page.toString())
            parameter("language", language)
        }.body()

    override suspend fun getSimilar(id: Int, page: Int, language: String): MovieListApiModel =
        client.get("/3/movie/$id/similar") {
            parameter("page", page.toString())
            parameter("language", language)
        }.body()

    private suspend inline fun getMovieList(
        path: String,
        page: Int,
        language: String,
        region: String
    ): MovieListApiModel =
        client.get("/3/movie/$path") {
            parameter("page", page.toString())
            parameter("language", language)
        }.body()
}
