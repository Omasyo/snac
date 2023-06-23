package com.quitr.snac.core.network

import com.quitr.snac.core.network.movielist.MovieListApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val Client by lazy {
    HttpClient(CIO) {
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(TmdbReadAccessToken, "")
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            host = "api.themoviedb.org"
        }
    }
}

@Serializable
data class Temp(
    val headquarters: String,
    val homepage: String,
    val id: Int,
    @SerialName("logo_path") val logoPath: String,
    val name: String,
    @SerialName("origin_country") val originCountry: String,
)

suspend fun main() {
    try {
        val response = Client.get("3/tv/popular") {
            parameters {
                append("page", "1")
            }
        }
        println(response)
        val temp: TvListApiModel = response.body()
        println(temp)
    } catch (e: Exception) {
        println(e)
    }
}