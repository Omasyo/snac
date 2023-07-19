package com.keetr.snac.core.data.repository.people

import com.keetr.snac.core.model.PersonDetails

interface PeopleRepository {
    suspend fun getDetails(id: Int, language: String = "") : Result<PersonDetails>
}