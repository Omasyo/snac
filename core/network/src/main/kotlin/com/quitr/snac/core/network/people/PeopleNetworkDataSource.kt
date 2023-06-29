package com.quitr.snac.core.network.people

import com.quitr.snac.core.network.people.models.PersonApiModel

interface PeopleNetworkDataSource {
    suspend fun getDetails(id:Int, language: String) : PersonApiModel
}