package com.quitr.snac.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get

val Client by lazy {
    HttpClient(CIO) {
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(TmdbReadAccessToken, "")
                }
            }
        }
        defaultRequest {
            host = "api.themoviedb.org"
        }
    }
}

//suspend fun main() {
//    println(Client.get("3/movie/11").body<String>())
//}