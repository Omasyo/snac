package com.quitr.snac.core.data

//TODO: mark as stable?
data class Show(
    val id: Int,
    val title: String,
    val rating: String,
    val posterUrl: String,
    val showType: ShowType
)
