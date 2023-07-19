package com.keetr.snac.core.network.people

import android.content.Context
import com.keetr.snac.core.network.createClient
import com.keetr.snac.core.network.people.fake.PersonDetailsResponse
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

class DefaultPeopleNetworkDataSourceTest {
    private lateinit var client: HttpClient

    @BeforeEach
    fun setUp(@TempDir dir: Path) {
        val mockEngine = MockEngine { request ->
            respond(
                headers = headersOf(HttpHeaders.ContentType, "application/json"),
                content = when(request.url.encodedPath) {
                    "/3/person/2" -> PersonDetailsResponse
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
        val details = DefaultPeopleNetworkDataSource(client).getDetails(2,"")
        assertEquals("Mark Hamill", details.name)
    }
}