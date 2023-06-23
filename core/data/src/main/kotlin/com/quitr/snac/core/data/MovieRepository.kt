package com.quitr.snac.core.data

import android.util.Log
import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.model.ShowType
import com.quitr.snac.core.network.MovieNetworkDataSource
import com.quitr.snac.core.network.getMovieNetworkDataSource
import com.quitr.snac.core.network.movielist.MovieApiModel
import kotlin.Error


const val TAG = "MovieRepository"
fun getMovieRepository(
//    private val localDataSource: MovieLocalDataSource
): MovieRepository = DefaultMovieRepository(getMovieNetworkDataSource())

enum class TimeWindow(val text: String) { Day("day"), Week("week") }

interface MovieRepository {

    suspend fun getTrending(
        page: Int,
        language: String = "",
        timeWindow: TimeWindow = TimeWindow.Day
    ): Response<List<Show>>

    suspend fun getNowPlaying(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    suspend fun getPopular(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    suspend fun getTopRated(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>

    suspend fun getUpcoming(
        page: Int,
        language: String = "",
        region: String = ""
    ): Response<List<Show>>
}

val temp = "https://image.tmdb.org/t/p/w342" //TODO

private class DefaultMovieRepository(
    private val networkDataSource: MovieNetworkDataSource,
//    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    override suspend fun getTrending(
        page: Int,
        language: String,
        timeWindow: TimeWindow
    ): Response<List<Show>> = try {
        val results = networkDataSource.getTrending(page, timeWindow.text, language).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTrending: $exception")
        Error
    }

    override suspend fun getNowPlaying(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getNowPlaying(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTrending: $exception")
        Error
    }

    override suspend fun getPopular(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getPopular(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTrending: $exception")
        Error
    }

    override suspend fun getTopRated(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getTopRated(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTrending: $exception")
        Error
    }

    override suspend fun getUpcoming(
        page: Int,
        language: String,
        region: String
    ): Response<List<Show>> = try {
        val results = networkDataSource.getUpcoming(page, language, region).results
        Success(results.map { movie -> movie.toShow() })
    } catch (exception: Exception) {
        Log.d(TAG, "getTrending: $exception")
        Error
    }
}

private fun MovieApiModel.toShow() =
    Show(id, title, voteAverage.toString(), temp + posterPath, ShowType.Movie)