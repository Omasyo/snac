package com.quitr.snac.navigation

import com.quitr.snac.core.data.SectionType

object SnacRoutes {
    const val root = "root"
    const val home = "home"

    const val sectionArg = "sectionType"
    const val section = "section/{$sectionArg}"
    fun section(sectionType: SectionType) = "section/$sectionType"

    const val movieArg = "movieId"
    const val movie = "movie/{$movieArg}"
    fun movie(id: Int) = "movie/$id"
}