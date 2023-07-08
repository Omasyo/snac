package com.quitr.snac.core.network.search

interface SearchNetworkDataSource {
    suspend fun searchAll(query: String, page: Int, language: String, adult: Boolean) : SearchApiModel
}