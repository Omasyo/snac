package com.keetr.snac.core.network.search

import android.content.Context
import com.keetr.snac.core.network.createClient
import com.keetr.snac.core.network.search.fake.SearchResponse
import com.keetr.snac.core.network.search.models.SearchPersonApiModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.nio.file.Path

internal class DefaultSearchNetworkDataSourceTest {
    private lateinit var client: HttpClient

    @BeforeEach
    fun setUp(@TempDir dir: Path) {
        val mockEngine = MockEngine { request ->
            respond(
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
                content = when (request.url.encodedPath) {
                    "/3/search/multi" -> SearchResponse
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
    fun searchAll() = runTest {
        val results = DefaultSearchNetworkDataSource(client).searchAll("James", 1, "", false)
        assertEquals(results.page, 1)
        assert(results.results.first() is SearchPersonApiModel)
        assertEquals((results.results.first() as SearchPersonApiModel).name, "James")
    }
}