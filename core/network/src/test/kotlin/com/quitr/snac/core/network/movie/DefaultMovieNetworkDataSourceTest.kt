package com.quitr.snac.core.network.movie

import android.content.Context
import com.quitr.snac.core.network.createClient
import com.quitr.snac.core.network.movie.fake.MovieDetailsResponse
import com.quitr.snac.core.network.movie.fake.NowPlayingResponse
import com.quitr.snac.core.network.movie.fake.PopularResponse
import com.quitr.snac.core.network.movie.fake.TopRatedResponse
import com.quitr.snac.core.network.movie.fake.TrendingResponse
import com.quitr.snac.core.network.movie.fake.UpcomingResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

class DefaultMovieNetworkDataSourceTest {
    private lateinit var client: HttpClient

    @BeforeEach
    fun setUp(@TempDir dir: Path) {
        val mockEngine = MockEngine { request ->
            respond(
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
                content = when(request.url.encodedPath) {
                    "/3/movie/697843" -> MovieDetailsResponse
                    "/3/trending/movie/day" -> TrendingResponse
                    "/3/movie/now_playing" -> NowPlayingResponse
                    "/3/movie/popular" -> PopularResponse
                    "/3/movie/top_rated" -> TopRatedResponse
                    "/3/movie/upcoming" -> UpcomingResponse
                    else -> throw Exception("Invalid url path")
                }
            )
        }
        val context = mockk<Context>()
        every { context.cacheDir } returns dir.toFile()
        client = createClient(mockEngine, context)
    }

    @AfterEach
    fun tearDown() {
        client.close()
    }

    @Test
    fun getDetails() = runTest {
        val details = DefaultMovieNetworkDataSource(client).getDetails(697843, "")
        assertEquals("Extraction 2", details.title)
        assert(details.genres.any { it.name == "Action" })
    }

    @Test
    fun getTrending() = runTest {
        val list = DefaultMovieNetworkDataSource(client).getTrending(1, "day","")
        assertEquals("Nimona", list.results.first().title)
    }

    @Test
    fun getNowPlaying()  = runTest {
        val list = DefaultMovieNetworkDataSource(client).getNowPlaying(1, "","")
        assertEquals("Insidious: The Red Door", list.results.last().title)
    }

    @Test
    fun getPopular()  = runTest {
        val list = DefaultMovieNetworkDataSource(client).getPopular(1, "","")
        assertEquals("Fast X", list.results.first().title)
    }

    @Test
    fun getTopRated()  = runTest {
        val list = DefaultMovieNetworkDataSource(client).getTopRated(1, "","")
        assertEquals("The Godfather", list.results.first().title)
    }

    @Test
    fun getUpcoming()  = runTest {
        val list = DefaultMovieNetworkDataSource(client).getUpcoming(1, "","")
        assertEquals("Elemental", list.results.first().title)
    }
}