package com.quitr.snac.core.network.tv

import android.content.Context
import com.quitr.snac.core.network.createClient
import com.quitr.snac.core.network.tv.fake.AiringTodayResponse
import com.quitr.snac.core.network.tv.fake.EpisodeDetailsResponse
import com.quitr.snac.core.network.tv.fake.OnTheAirResponse
import com.quitr.snac.core.network.tv.fake.PopularResponse
import com.quitr.snac.core.network.tv.fake.SeasonDetailsResponse
import com.quitr.snac.core.network.tv.fake.TopRatedResponse
import com.quitr.snac.core.network.tv.fake.TrendingResponse
import com.quitr.snac.core.network.tv.fake.TvDetailsResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

class DefaultTvNetworkDataSourceTest {
    private lateinit var client: HttpClient

    @BeforeEach
    fun setUp(@TempDir dir: Path) {
        val mockEngine = MockEngine { request ->
            respond(
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
                content = when(request.url.encodedPath) {
                    "/3/tv/60625" -> TvDetailsResponse
                    "/3/trending/tv/day" -> TrendingResponse
                    "/3/tv/airing_today" -> AiringTodayResponse
                    "/3/tv/on_the_air" -> OnTheAirResponse
                    "/3/tv/popular" -> PopularResponse
                    "/3/tv/top_rated" -> TopRatedResponse
                    "/3/tv/51964/season/1" -> SeasonDetailsResponse
                    "/3/tv/51964/season/1/episode/2" -> EpisodeDetailsResponse
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
        val details = DefaultTvNetworkDataSource(client).getDetails(60625, "")
        assertEquals("Dan Harmon", details.createdBy.first().name)
        assertEquals("Rick and Morty", details.name)
        assertEquals(7, details.seasons.size)
    }

    @Test
    fun getTrending() = runTest {
        val res = DefaultTvNetworkDataSource(client).getTrending(
                1,
                "day",
                ""
            )
        assertEquals("The Witcher", res.results.first().name)
    }

    @Test
    fun getAiringToday() = runTest {
        val res = DefaultTvNetworkDataSource(client).getAiringToday(
            1,
            "",
            ""
        )
        assertEquals(res.page, 1)
        assertEquals("Tagesschau", res.results.first().name)
    }

    @Test
    fun getOnTheAir() = runTest {
        val res = DefaultTvNetworkDataSource(client).getOnTheAir(
            1,
            "",
            ""
        )
        assertEquals(res.page, 1)
        assertEquals("Flor Sem Tempo", res.results.last().name)
    }

    @Test
    fun getPopular()  = runTest {
        val res = DefaultTvNetworkDataSource(client).getPopular(
            1,
            "",
        )
        assertEquals(res.page, 1)
        assertEquals("The Witcher", res.results.last().name)
    }

    @Test
    fun getTopRated() = runTest {
        val res = DefaultTvNetworkDataSource(client).getTopRated(
            1,
            "",
        )
        assertEquals(res.page, 1)
        assertEquals("Breaking Bad", res.results.first().name)
    }

    @Test
    fun getSeasonDetails() = runTest {
        val res = DefaultTvNetworkDataSource(client).getSeasonDetails(
            51964, 1, ""
        )
        assertEquals(res.id, 51964)
        assertEquals(res.episodes.size, 3)
        assertEquals(res.name, "Season 1")
    }

    @Test
    fun getEpisodeDetails() = runTest {
        val res = DefaultTvNetworkDataSource(client).getEpisodeDetails(
            51964, 1, 2, ""
        )
        assertEquals(res.name, "Fifteen Million Merits")
        assertEquals(res.seasonNumber, 1)
        assertEquals(res.episodeNumber, 2)
    }
}