package com.quitr.snac.core.data.movie

import androidx.paging.PagingData
import com.quitr.snac.core.data.Response
import com.quitr.snac.core.data.TimeWindow
import com.quitr.snac.core.model.Movie
import com.quitr.snac.core.model.Person
import com.quitr.snac.core.model.Show
import com.quitr.snac.core.network.movie.list.MovieApiModel
import com.quitr.snac.core.network.movie.models.CrewApiModel
import com.quitr.snac.core.network.movie.models.ProductionCompanyApiModel
import kotlinx.coroutines.flow.Flow

fun Person.addRole(other: String) = copy(
    role = "$role, $other"
)

internal fun List<Person>.combineSimilar() = fold(mutableMapOf<Int, Person>()) { result, person ->
    result[person.id] = if (!result.containsKey(person.id)) {
        person
    } else {
        result[person.id]!!.addRole(person.role)
    }
    result
}.map { (key, value) -> value }

internal fun List<CrewApiModel>.toPeople() = map { crew -> crew.toPerson() }


internal fun List<ProductionCompanyApiModel>.toNames() =
    map { productionCompanyApiModel -> productionCompanyApiModel.name }


internal fun List<MovieApiModel>.toShows() = map { movieApiModel -> movieApiModel.toShow() }


interface MovieRepository {
    suspend fun getDetails(id: Int, language: String = ""): Response<Movie>

    suspend fun getTrending(
        page: Int, language: String = "", timeWindow: TimeWindow = TimeWindow.Day
    ): Response<List<Show>>

    fun getTrendingStream(
        language: String = "", timeWindow: TimeWindow = TimeWindow.Day
    ): Flow<PagingData<Show>>

    suspend fun getNowPlaying(
        page: Int, language: String = "", region: String = ""
    ): Response<List<Show>>

    fun getNowPlayingStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getPopular(
        page: Int, language: String = "", region: String = ""
    ): Response<List<Show>>

    fun getPopularStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getTopRated(
        page: Int, language: String = "", region: String = ""
    ): Response<List<Show>>

    fun getTopRatedStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>

    suspend fun getUpcoming(
        page: Int, language: String = "", region: String = ""
    ): Response<List<Show>>

    fun getUpcomingStream(
        language: String = "", region: String = ""
    ): Flow<PagingData<Show>>
}