package com.keetr.snac.core.network.search

import com.keetr.snac.core.network.search.models.SearchApiModel

interface SearchNetworkDataSource {
    suspend fun searchAll(query: String, page: Int, language: String, adult: Boolean) : SearchApiModel
}