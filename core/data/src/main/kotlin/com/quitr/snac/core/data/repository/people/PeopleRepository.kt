package com.quitr.snac.core.data.repository.people

import com.quitr.snac.core.model.PersonDetails

interface PeopleRepository {
    suspend fun getDetails(id: Int, language: String = "") : Result<PersonDetails>
}