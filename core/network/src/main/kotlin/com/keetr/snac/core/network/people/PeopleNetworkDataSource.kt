package com.keetr.snac.core.network.people

import com.keetr.snac.core.network.people.models.PersonApiModel

interface PeopleNetworkDataSource {
    suspend fun getDetails(id:Int, language: String) : PersonApiModel
}