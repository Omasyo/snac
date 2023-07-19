package com.keetr.snac.core.network.movie

import com.keetr.snac.core.network.movie.models.MovieDetailsApiModel
import com.keetr.snac.core.network.movie.models.MovieListApiModel

interface MovieNetworkDataSource {
    suspend fun getDetails(id: Int, language: String): MovieDetailsApiModel

    suspend fun getTrending(page: Int, timeWindow: String, language: String): MovieListApiModel

    suspend fun getNowPlaying(page: Int, language: String, region: String): MovieListApiModel

    suspend fun getPopular(page: Int, language: String, region: String): MovieListApiModel

    suspend fun getTopRated(page: Int, language: String, region: String): MovieListApiModel

    suspend fun getUpcoming(page: Int, language: String, region: String): MovieListApiModel

//    suspend fun getRecommendation(id: Int, page: Int, language: String): RecommendationsApiModel
//
//    suspend fun getSimilar(id: Int, page: Int, language: String): MovieListApiModel
}
