package com.quitr.snac.core.data.people

import com.quitr.snac.core.data.Response
import com.quitr.snac.core.model.PersonDetails

interface PeopleRepository {
    suspend fun getDetails(id: Int, language: String = "") : Response<PersonDetails>
}