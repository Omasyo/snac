package com.quitr.snac.navigation

import com.quitr.snac.core.data.SectionType

object SnacRoutes {
    const val root = "root"
    const val home = "home"

    const val section = "section/{sectionType}"
    fun section(sectionType: SectionType) = "section/$sectionType"
}