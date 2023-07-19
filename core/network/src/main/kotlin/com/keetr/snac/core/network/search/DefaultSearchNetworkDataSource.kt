package com.keetr.snac.core.network.search

import com.keetr.snac.core.network.search.models.SearchApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class DefaultSearchNetworkDataSource @Inject constructor(private val client: HttpClient) :
    SearchNetworkDataSource {
    override suspend fun searchAll(
        query: String,
        page: Int,
        language: String,
        adult: Boolean
    ): SearchApiModel = client.get("/3/search/multi") {
        parameter("query", query)
        parameter("page", page)
        parameter("include_adult", adult)
        parameter("language", language)
    }.body()
}