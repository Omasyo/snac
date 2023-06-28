package com.quitr.snac.core.network.movie

import com.quitr.snac.core.network.movie.list.MovieListApiModel
import com.quitr.snac.core.network.movie.models.MovieDetailsApiModel

interface MovieNetworkDataSource {
    suspend fun getDetails(id: Int, language: String) : MovieDetailsApiModel

    suspend fun getTrending(page: Int, timeWindow: String, language: String): MovieListApiModel

    suspend fun getNowPlaying(page: Int, language: String, region: String): MovieListApiModel

    suspend fun getPopular(page: Int, language: String, region: String): MovieListApiModel

    suspend fun getTopRated(page: Int, language: String, region: String): MovieListApiModel

    suspend fun getUpcoming(page: Int, language: String, region: String): MovieListApiModel
}
