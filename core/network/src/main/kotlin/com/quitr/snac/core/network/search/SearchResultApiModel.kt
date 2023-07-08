package com.quitr.snac.core.network.search

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("media_type")
sealed class SearchResultApiModel
