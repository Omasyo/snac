package com.quitr.snac.core.network.search

import com.quitr.snac.core.network.search.models.SearchApiModel

interface SearchNetworkDataSource {
    suspend fun searchAll(query: String, page: Int, language: String, adult: Boolean) : SearchApiModel
}