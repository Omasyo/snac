package com.quitr.snac.core.data

import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.MovieNetworkDataSource

interface MovieRepository {
    suspend fun getNowPlaying(page: Int, language: String, region: String): List<Show>

    suspend fun getPopular(page: Int, language: String, region: String): List<Show>

    suspend fun getTopRated(page: Int, language: String, region: String): List<Show>

    suspend fun getUpcoming(page: Int, language: String, region: String): List<Show>
}

private class DefaultMovieRepository(
    private val networkDataSource: MovieNetworkDataSource,
//    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun getNowPlaying(page: Int, language: String, region: String): List<Show> {
        val results = networkDataSource.getNowPlaying(page, language, region).results
        return results.map { movie ->
            with(movie) {
                Show(id, title, voteAverage.toString(), posterPath, ShowType.Movie)
            }
        }
    }

    override suspend fun getPopular(page: Int, language: String, region: String): List<Show> {
        TODO("Not yet implemented")
    }

    override suspend fun getTopRated(page: Int, language: String, region: String): List<Show> {
        TODO("Not yet implemented")
    }

    override suspend fun getUpcoming(page: Int, language: String, region: String): List<Show> {
        TODO("Not yet implemented")
    }
}