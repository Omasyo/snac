package com.quitr.snac.core.network

import com.quitr.snac.core.network.tv.list.TvListApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.request
import io.ktor.http.authority
import io.ktor.http.fullPath
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

fun createClient() =
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
                coerceInputValues = true
            })
        }
        install(HttpCache)
        defaultRequest {
            host = "api.themoviedb.org"
        }
    }
