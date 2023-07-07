package com.quitr.snac.core.network.people

import com.quitr.snac.core.network.people.models.PersonApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

internal class DefaultPeopleNetworkDataSource @Inject constructor(private val client: HttpClient) :
    PeopleNetworkDataSource {
    override suspend fun getDetails(id: Int, language: String): PersonApiModel =
        client.get("/3/person/$id") {
            parameter("language", language)
            parameter("append_to_response", "movie_credits,tv_credits")
        }.body()
}