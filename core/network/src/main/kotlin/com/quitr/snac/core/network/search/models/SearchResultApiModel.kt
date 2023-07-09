package com.quitr.snac.core.network.search.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("media_type")
sealed class SearchResultApiModel
